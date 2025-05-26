package com.totwo.chat.dto;

import lombok.Builder;

@Builder
public record ResponseChatRoomDto(
        String roomId,
        String title,
        String message,
        int count
) {
}