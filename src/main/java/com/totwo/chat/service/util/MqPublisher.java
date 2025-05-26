package com.totwo.chat.service.util;

import com.totwo.chat.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MqPublisher {
    private final JmsTemplate jmsTemplate;

    public void publish(ChatMessage message) {
        jmsTemplate.convertAndSend("chat.topic", message);
    }
}