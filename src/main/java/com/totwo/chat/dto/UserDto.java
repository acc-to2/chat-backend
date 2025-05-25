package com.totwo.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 프로필 정보를 주고받기 위한 DTO 클래스입니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String email;      // 예: u1@gmail.com, u2@gmail.com (prefix 없는 상태)
    private String nickName;
}
