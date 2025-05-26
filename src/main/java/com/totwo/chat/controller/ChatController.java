package com.totwo.chat.controller;

import com.totwo.chat.common.CommonResponse;
import com.totwo.chat.dto.ChatRoomDto;
import com.totwo.chat.dto.MessageDto;
import com.totwo.chat.security.AuthUtils;
import com.totwo.chat.service.ChatRoomService;
import com.totwo.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
