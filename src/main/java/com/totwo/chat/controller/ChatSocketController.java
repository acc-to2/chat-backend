package com.totwo.chat.controller;

import com.totwo.chat.common.CommonResponse;
import com.totwo.chat.dto.UserDto;
import com.totwo.chat.entity.ChatMessage;
import com.totwo.chat.service.MessageService;
import com.totwo.chat.service.UserService;
import com.totwo.chat.service.util.MqPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatSocketController {

    private final UserService userService;
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/{room_id}/send")
    public void send(@DestinationVariable("room_id") String roomId, ChatMessage message) {
        message.setRoomId(roomId);
        log.info("User in subscribe room: {}", roomId);
        message.setType("SEND");
        message.setTimestamp(LocalDateTime.now());
        userService.getUserByEmail(message.getSenderId())
                .ifPresent(user -> message.setSenderName(user.getNickName()));

        messageService.saveMessage(
                roomId,
                message.getSenderId(),
                message.getContent(),
                String.valueOf(message.getTimestamp().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli())
        );

        messagingTemplate.convertAndSend("/chat/" + roomId + "/in", message);
    }
}
