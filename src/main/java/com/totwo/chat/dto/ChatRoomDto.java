package com.totwo.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 채팅방 메타데이터를 담는 DTO 클래스입니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDto {
    /**
     * 채팅방 ID
     */
    private String roomId;

    /**
     * 채팅방 이름 <br/>
     * 그룹 채팅방의 경우는 그룹 이름, 1:1 채팅방의 경우는 null
     */
    private String roomName;

    /**
     * 그룹 채팅방 여부 <br/>
     * true면 그룹 채팅방, false면 1:1 채팅방
     */
    private boolean isGroup;
}
