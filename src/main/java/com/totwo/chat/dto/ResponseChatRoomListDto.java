package com.totwo.chat.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ResponseChatRoomListDto(
        List<ResponseChatRoomDto> groupRoomList,
        List<ResponseChatRoomDto> privateRoomList
) {
}