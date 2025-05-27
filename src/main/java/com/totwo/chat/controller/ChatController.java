package com.totwo.chat.controller;

import com.totwo.chat.common.CommonResponse;
import com.totwo.chat.dto.*;
import com.totwo.chat.security.AuthUtils;
import com.totwo.chat.service.ChatRoomService;
import com.totwo.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {
    private final AuthUtils authUtils;
    private final MessageService messageService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/room/list")
    public ResponseEntity<CommonResponse<ResponseChatRoomListDto>> getChatRoomList() {
        String email = authUtils.getEmail();
        List<ResponseChatRoomDto> groupRoomList = new ArrayList<>();
        List<ResponseChatRoomDto> privateRoomList = new ArrayList<>();
        List<ChatRoomDto> roomList = chatRoomService.getChatRoomsByUser(email);
        for (ChatRoomDto room : roomList) {
            ResponseChatRoomDto tmpRoom = ResponseChatRoomDto.builder()
                    .roomId(room.getRoomId())
                    .title(room.getRoomName())
                    .message(room.getLastMessage().getContent())
                    .count(room.getUnreadMessageCount())
                    .build();

            if(room.isGroup()) {
                groupRoomList.add(tmpRoom);
            }
            else {
                privateRoomList.add(tmpRoom);
            }
        }
        ResponseChatRoomListDto chatRoom = ResponseChatRoomListDto.builder()
                .groupRoomList(groupRoomList)
                .privateRoomList(privateRoomList)
                .build();

        return CommonResponse.ok(chatRoom);
    }

    @GetMapping("/{room_id}/list/get")
    public ResponseEntity<CommonResponse<List<MessageDto>>> getList(@PathVariable(name = "room_id") String roomId) {
        List<MessageDto> chatList = messageService.getMessagesByRoom(roomId);
        return CommonResponse.ok(chatList);
    }

    @PostMapping("/{room_id}/add-friend")
    public ResponseEntity<CommonResponse<Object>> addFriend(@PathVariable(name = "room_id") String roomId) {
        String email = authUtils.getEmail();
        chatRoomService.addUserToChatRoom(roomId, email);
        return CommonResponse.created(null);
    }

    @PostMapping("/room/create")
    public ResponseEntity<CommonResponse<Object>> createRoom(@RequestBody RequestCreateRoomDto requestDto) {
        String email = authUtils.getEmail();
        log.info("email: {}", email);
        String roomId = chatRoomService.createChatRoom(requestDto.title(), requestDto.isGroup());
        log.info("roomId: {}", roomId);
        if(requestDto.isGroup()) { // 그룹 채팅방일 때
            log.info("그룹 채팅방 입니다");
        }
        chatRoomService.addUserToChatRoom(roomId, email);
        for(String e : requestDto.emailList()) {
            chatRoomService.addUserToChatRoom(roomId, e);
        }

        return CommonResponse.created(roomId);
    }

    @DeleteMapping("/{room_id}/delete")
    public ResponseEntity<CommonResponse<Object>> deleteRoom(@PathVariable(name = "room_id") String roomId) {
        chatRoomService.deleteChatRoom(roomId);
        return CommonResponse.ok(null);
    }
}
