package com.totwo.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 개별 메세지에 대한 정보를 담는 DTO 클래스입니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    /**
     * 메세지 ID (timestamp_uuid 형태, 사전순으로 정렬하면 시간순이 됨)
     */
    private String messageId;

    /**
     * 보낸 사람의 이메일
     */
    private String senderEmail;

    /**
     * 보낸 사람의 닉네임
     */
    private String senderName;

    /**
     * 메세지 내용
     */
    private String content;

    /**
     * 메세지 전송 시간
     */
    private String timestamp;
}
