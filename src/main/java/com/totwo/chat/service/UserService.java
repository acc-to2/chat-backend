package com.totwo.chat.service;

import com.totwo.chat.dto.UserDto;

/**
 * 사용자 프로필 관련 로직을 정의하는 서비스 인터페이스입니다.
 * <p>
 * 이 인터페이스는 사용자 정보를 저장하고 조회하는 메서드를 제공합니다.
 * </p>
 */
public interface UserService {

    /**
     * 사용자 정보를 저장합니다. (사용할 일은 없을 것 같지만, 혹시나)
     *
     * @param userDto 저장할 사용자 정보
     */
    void saveUser(UserDto userDto);

    /**
     * 이메일을 기반으로 사용자 정보를 조회합니다.
     *
     * @param email 조회할 사용자의 이메일
     * @return 조회된 사용자 정보
     */
    UserDto getUserByEmail(String email);
}