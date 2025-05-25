package com.totwo.chat.service;

import com.totwo.chat.dto.UserDto;

import java.util.List;

/**
 * 친구 관련 로직을 정의하는 서비스 인터페이스입니다.
 * <p>
 * 이 인터페이스는 친구 등록, 삭제 및 친구 목록 조회와 관련된 메서드를 제공합니다.
 * </p>
 */
public interface FriendService {
    /**
     * 친구를 동록합니다.
     *
     * @param userEmail 사용자의 이메일
     * @param friendEmail 친구로 등록할 사용자의 이메일
     */
    void registerFriend(String userEmail, String friendEmail);

    /**
     * 친구를 삭제합니다.
     *
     * @param userEmail 사용자의 이메일
     * @param friendEmail 삭제할 친구의 이메일
     */
    void deleteFriend(String userEmail, String friendEmail);

    /**
     * 친구 목록을 조회합니다.
     *
     * @param userEmail 사용자의 이메일
     * @return 친구 목록
     */
    List<UserDto> getFriends(String userEmail);
}
