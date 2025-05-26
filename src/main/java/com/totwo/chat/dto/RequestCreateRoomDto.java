package com.totwo.chat.dto;

import java.util.List;

public record RequestCreateRoomDto(
        String title, // 채팅방 이름
        Boolean isGroup, // 그룹 여부
        List<String> emailList // 참여 이메일 리스트
) {
}
