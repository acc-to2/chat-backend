package com.totwo.chat.service.impl;

import com.totwo.chat.dto.MessageDto;
import com.totwo.chat.entity.Message;
import com.totwo.chat.entity.UserRoomParticipation;
import com.totwo.chat.repository.ChatRoomRepository;
import com.totwo.chat.repository.MessageRepository;
import com.totwo.chat.repository.UserRoomParticipationRepository;
import com.totwo.chat.service.MessageService;
import com.totwo.chat.service.util.PrefixUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRoomParticipationRepository userRoomParticipationRepository;

    @Override
    public void saveMessage(String roomId, String senderEmail, String content, String timestamp) {
        String roomIdWithPrefix = PrefixUtil.withRoomPrefix(roomId);

        validateChatRoomExists(roomIdWithPrefix);

        String uuid = UUID.randomUUID().toString();

        messageRepository.save(
                Message.builder()
                        .pk(roomIdWithPrefix)
                        .sk(timestamp + "_" + uuid)
                        .content(content)
                        .timestamp(timestamp)
                        .build()
        );
    }

    @Override
    public List<MessageDto> getMessagesByRoom(String roomId) {
        String roomIdWithPrefix = PrefixUtil.withRoomPrefix(roomId);

        validateChatRoomExists(roomIdWithPrefix);

        return messageRepository.loadSkBeginsWith(roomIdWithPrefix, PrefixUtil.MSG_PREFIX)
                .stream()
                .map(message -> MessageDto.builder()
                        .messageId(PrefixUtil.removeMsgPrefix(message.getSk()))
                        .senderEmail(message.getSenderEmail())
                        .content(message.getContent())
                        .timestamp(Instant.ofEpochMilli(Long.parseLong(message.getTimestamp()))
                                .atZone(ZoneId.of("Asia/Seoul")).toString())
                        .build())
                .toList();
    }

    @Override
    public int countUnreadMessages(String roomId, String userEmail) {
        return userRoomParticipationRepository
                .load(PrefixUtil.withUserPrefix(userEmail), PrefixUtil.withRoomPrefix(roomId))
                .map(UserRoomParticipation::getLastReadMessage)
                .map(message -> messageRepository
                        .countMessagesAfter(PrefixUtil.withRoomPrefix(roomId), message))
                .orElse(0);
    }

    @Override
    public Optional<MessageDto> getLastMessage(String roomId) {
        return messageRepository.getLastMessage(PrefixUtil.withRoomPrefix(roomId))
                .map(message -> MessageDto.builder()
                        .messageId(PrefixUtil.removeMsgPrefix(message.getSk()))
                        .senderEmail(message.getSenderEmail())
                        .content(message.getContent())
                        .timestamp(Instant.ofEpochMilli(Long.parseLong(message.getTimestamp()))
                                .atZone(ZoneId.of("Asia/Seoul")).toString())
                        .build());
    }

    private void validateChatRoomExists(String roomIdWithPrefix) {
        chatRoomRepository.load(roomIdWithPrefix, "METADATA")
                .orElseThrow(() -> new IllegalArgumentException("Chat room not found: " + roomIdWithPrefix));
    }
}
