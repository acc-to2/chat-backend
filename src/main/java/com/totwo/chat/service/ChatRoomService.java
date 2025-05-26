package com.totwo.chat.service;

import com.totwo.chat.dto.ChatRoomDto;
import com.totwo.chat.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * 채팅방 관련 로직을 정의하는 서비스 인터페이스입니다.
 * <p>
 * 이 인터페이스는 채팅방 생성, 삭제, 조회 및 참여자 관리와 관련된 메서드를 제공합니다.
 * </p>
 */
public interface ChatRoomService {
    /**
     * 사용자가 채팅방에서 일시적으로 나갈 때 마지막으로 읽은 메세지를 업데이트하는 용도입니다.
     */
    void updateLastReadMessage(String roomId, String userEmail);

    /**
     * 사용자가 참여중인 채팅방 목록을 조회합니다.
     * <p>
     *     추가: 채팅방의 마지막 메세지와 해당 사용자가 읽지 않은 메세지 수를 포함하여 반환합니다.
     *     채팅방에 메세지가 없을 경우 마지막 메세지는 null로 설정됩니다.
     * </p>
     * @param userEmail 사용자의 이메일
     */
    List<ChatRoomDto> getChatRoomsByUser(String userEmail);

    /**
     * 채팅방에 참여중인 사용자 목록을 조회합니다.
     */
    List<UserDto> getUsersInChatRoom(String roomId);

    /**
     * 채팅방에 사용자를 추가합니다.
     *
     * @param roomId 채팅방 ID
     * @param userEmail 추가할 사용자의 이메일
     */
    void addUserToChatRoom(String roomId, String userEmail);

    /**
     * 채팅방에서 사용자를 삭제합니다.
     *
     * @param roomId 채팅방 ID
     * @param userEmail 제거할 사용자의 이메일
     */
    void removeUserFromChatRoom(String roomId, String userEmail);

    /**
     * 채팅방을 생성합니다.
     *
     * @param roomName 채팅방 이름 (그룹 채팅방의 경우는 그룹 이름, 1:1 채팅방의 경우는 null)
     * @param isGroup  그룹 채팅방 여부 (true면 그룹 채팅방, false면 1:1 채팅방)
     */
    String createChatRoom(String roomName, boolean isGroup);

    /**
     * 채팅방을 삭제합니다.
     *
     * @param roomId 삭제할 채팅방 ID
     */
    void deleteChatRoom(String roomId);

    /**
     * 채팅방 정보를 조회합니다.
     *
     * @param roomId 조회할 채팅방 ID
     * @return 채팅방 정보 DTO
     */
    Optional<ChatRoomDto> getChatRoomById(String roomId);
}
