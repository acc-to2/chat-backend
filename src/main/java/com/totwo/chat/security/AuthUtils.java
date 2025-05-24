package com.totwo.chat.security;

import com.totwo.chat.dto.CustomUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    public CustomUserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal principal) {
            return principal;
        }
        throw new IllegalStateException("인증되지 않은 사용자입니다.");
    }

    public String getEmail() {
        return getCurrentUser().email();
    }

    public String getNickname() {
        return getCurrentUser().nickname();
    }
}

