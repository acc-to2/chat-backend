package com.totwo.chat.service;

import com.totwo.chat.dto.MessageDto;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    /**
     * 채팅방에 메세지를 저장합니다.
     *
     * @param roomId 채팅방 ID
     * @param senderEmail 메세지를 보낸 사람의 이메일
     * @param content 메세지 내용
     * @param timestamp 메세지의 타임스탬프 (밀리초 단위)
     */
    void saveMessage(String roomId, String senderEmail, String content, String timestamp);

    /**
     * 채팅방의 메세지 목록을 조회합니다.
     * <p>
     *     해당 채팅방에 저장된 모든 메세지를 조회하며, 시간순 정렬로 반환됩니다.
     * </p>
     * @param roomId 조회할 채팅방 ID
     * @return 메세지 목록
     */
    List<MessageDto> getMessagesByRoom(String roomId);

    /**
     * 채팅방에서 특정 유저가 읽지 않은 메세지 수를 조회합니다.
     *
     * @param roomId 조회할 채팅방 ID
     * @param userEmail 조회할 유저의 이메일
     * @return 메세지 수
     */
    int countUnreadMessages(String roomId, String userEmail);

    /**
     * 채팅방의 마지막 메세지를 조회합니다.
     *
     * @param roomId 조회할 채팅방 ID
     * @return 마지막 메세지
     */
    Optional<MessageDto> getLastMessage(String roomId);
}
