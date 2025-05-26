package com.totwo.chat.service.impl;

import com.totwo.chat.dto.ChatRoomDto;
import com.totwo.chat.dto.UserDto;
import com.totwo.chat.entity.ChatRoom;
import com.totwo.chat.entity.ChatRoomParticipant;
import com.totwo.chat.entity.UserRoomParticipation;
import com.totwo.chat.repository.*;
import com.totwo.chat.service.ChatRoomService;
import com.totwo.chat.service.UserService;
import com.totwo.chat.service.util.PrefixUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final UserService userService;

    private final ChatRoomRepository chatRoomRepository;
    private final UserRoomParticipationRepository userRoomParticipationRepository;
    private final ChatRoomParticipantRepository chatRoomParticipantRepository;
    private final MessageRepository messageRepository;

    @Override
    public List<ChatRoomDto> getChatRoomsByUser(String userEmail) {
        return userRoomParticipationRepository
                .loadSkBeginsWith(PrefixUtil.withUserPrefix(userEmail), PrefixUtil.ROOM_PREFIX)
                .stream()
                .map(userRoom -> getChatRoomById(PrefixUtil.removeRoomPrefix(userRoom.getSk())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public List<UserDto> getUsersInChatRoom(String roomId) {
        return chatRoomParticipantRepository
                .loadSkBeginsWith(PrefixUtil.withRoomPrefix(roomId), PrefixUtil.USER_PREFIX)
                .stream()
                .map(roomUser -> userService.getUserByEmail(PrefixUtil.removeUserPrefix(roomUser.getSk())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public void addUserToChatRoom(String roomId, String userEmail) {
        if (userService.getUserByEmail(userEmail).isEmpty()) {
            throw new IllegalArgumentException("User not found: " + userEmail);
        }
        userRoomParticipationRepository.save(
                UserRoomParticipation.builder()
                        .pk(PrefixUtil.withUserPrefix(userEmail))
                        .sk(PrefixUtil.withRoomPrefix(roomId))
                        .build());
        chatRoomParticipantRepository.save(
                ChatRoomParticipant.builder()
                        .pk(PrefixUtil.withRoomPrefix(roomId))
                        .sk(PrefixUtil.withUserPrefix(userEmail))
                        .build());
    }

    @Override
    public void removeUserFromChatRoom(String roomId, String userEmail) {
        userRoomParticipationRepository.delete(
                PrefixUtil.withUserPrefix(userEmail),
                PrefixUtil.withRoomPrefix(roomId)
                );
        chatRoomParticipantRepository.delete(
                PrefixUtil.withRoomPrefix(roomId),
                PrefixUtil.withUserPrefix(userEmail)
        );
    }

    @Override
    public void createChatRoom(String roomName, boolean isGroup) {
        String roomId = UUID.randomUUID().toString();

        chatRoomRepository.save(
                ChatRoom.builder()
                        .pk(PrefixUtil.withRoomPrefix(roomId))
                        .sk("METADATA")
                        .roomName(roomName)
                        .isGroup(isGroup)
                        .build());
    }

    @Override
    public void deleteChatRoom(String roomId) {  // 채팅방 포함 참여정보, 메세지까지 삭제
        String roomIdWithPrefix = PrefixUtil.withRoomPrefix(roomId);

        chatRoomRepository.delete(roomIdWithPrefix, "METADATA");
        chatRoomParticipantRepository.loadSkBeginsWith(roomIdWithPrefix, PrefixUtil.USER_PREFIX)
                .forEach(roomUser ->
                        removeUserFromChatRoom(roomId, PrefixUtil.removeUserPrefix(roomUser.getSk())));
        messageRepository.deleteSkBeginsWith(roomIdWithPrefix, PrefixUtil.MSG_PREFIX);
    }

    @Override
    public Optional<ChatRoomDto> getChatRoomById(String roomId) {
        return chatRoomRepository.load(PrefixUtil.withRoomPrefix(roomId), "METADATA")
                .map(room -> ChatRoomDto.builder()
                        .roomId(roomId)
                        .roomName(room.getRoomName())
                        .isGroup(room.getIsGroup())
                        .build());
    }
}
