package com.totwo.chat.service.util;

import com.totwo.chat.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MqListener {

    private final SimpMessagingTemplate messagingTemplate;

    @JmsListener(destination = "chat.topic")
    public void receive(ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/room/" + message.getRoomId(), message);
    }
}