package com.totwo.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    /**
     * 채팅방의 마지막 메세지
     */
    private MessageDto lastMessage;

    /**
     * 해당 사용자가 읽지 않은 메세지 수
     */
    private int unreadMessageCount;
}
