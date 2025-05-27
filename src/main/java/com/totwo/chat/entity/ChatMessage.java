package com.totwo.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String roomId;
    private String senderId;
    private String senderName; // Optional, can be set later
    private String type; // "IN", "OUT", "SEND"
    private String content;
    private LocalDateTime timestamp;
}