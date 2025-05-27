package com.totwo.chat.controller;

import com.totwo.chat.entity.ChatMessage;
import com.totwo.chat.service.MessageService;
import com.totwo.chat.service.util.MqPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Controller
public class ChatSocketController {

    private final MqPublisher publisher;
    private final MessageService messageService;

    @MessageMapping("/{room_id}/in")
    public void join(@DestinationVariable String roomId, ChatMessage message) {
        message.setRoomId(roomId);
        message.setType("IN");
        publisher.publish(message);
    }

    @MessageMapping("/{room_id}/out")
    public void leave(@DestinationVariable String roomId, ChatMessage message) {
        message.setRoomId(roomId);
        message.setType("OUT");
        publisher.publish(message);
    }

    @MessageMapping("/{room_id}/send")
    public void send(@DestinationVariable String roomId, ChatMessage message) {
        message.setRoomId(roomId);
        message.setType("SEND");
        message.setTimestamp(LocalDateTime.now());
        messageService.saveMessage(
                roomId,
                message.getSenderId(),
                message.getContent(),
                String.valueOf(message.getTimestamp().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli())
        );
        publisher.publish(message);
    }
}
