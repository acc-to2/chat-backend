package com.totwo.chat.controller;

import com.totwo.chat.entity.ChatMessage;
import com.totwo.chat.service.MessageService;
import com.totwo.chat.service.util.MqPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatSocketController {

    private final MqPublisher publisher;
    private final MessageService messageService;

    @MessageMapping("/{room_id}/send")
    @SendTo("/{room_id}/in")
    public ChatMessage send(@DestinationVariable("room_id") String roomId, ChatMessage message) {
        message.setRoomId(roomId);
        log.info("User in subscribe room: {}", roomId);
        message.setType("SEND");
        message.setTimestamp(LocalDateTime.now());
        messageService.saveMessage(
                roomId,
                message.getSenderId(),
                message.getContent(),
                String.valueOf(message.getTimestamp().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli())
        );
        return message;
    }
}
