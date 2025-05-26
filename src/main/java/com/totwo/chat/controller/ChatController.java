package com.totwo.chat.controller;

import com.totwo.chat.common.CommonResponse;
import com.totwo.chat.dto.ChatRoomDto;
import com.totwo.chat.dto.MessageDto;
import com.totwo.chat.dto.RequestCreateRoomDto;
import com.totwo.chat.security.AuthUtils;
import com.totwo.chat.service.ChatRoomService;
import com.totwo.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {
    private final AuthUtils authUtils;
    private final MessageService messageService;
    private final ChatRoomService chatRoomService;

//    @GetMapping("/room/list/group")
//    public List<ChatRoomDto> getGroupChatRoomList() {
//        return
//    }

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
        String roomId = chatRoomService.createChatRoom(requestDto.title(), requestDto.isGroup());
        if(requestDto.isGroup()) { // 그룹 채팅방일 때
            for(String e : requestDto.emailList()) {
                chatRoomService.addUserToChatRoom(roomId, e);
            }
        }
        else { // 일대일 채팅방일 때
            chatRoomService.addUserToChatRoom(roomId, email);
        }

        return CommonResponse.created(roomId);
    }
}
