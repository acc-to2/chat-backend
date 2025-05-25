package com.totwo.chat.security;

import com.totwo.chat.dto.CustomUserPrincipal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String email = jwt.getClaimAsString("email");
//        String nickname = jwt.getSubject();
        CustomUserPrincipal principal = new CustomUserPrincipal(email);
        return new UsernamePasswordAuthenticationToken(principal, jwt, Collections.emptyList());
    }
}
