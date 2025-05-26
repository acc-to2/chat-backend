package com.totwo.chat.service.impl;

import com.totwo.chat.dto.UserDto;
import com.totwo.chat.entity.User;
import com.totwo.chat.repository.UserRepository;
import com.totwo.chat.service.UserService;
import com.totwo.chat.service.util.PrefixUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(@NonNull UserDto userDto) {
        userRepository.save(
                User.builder()
                        .pk(PrefixUtil.withUserPrefix(userDto.getEmail()))
                        .sk("PROFILE")
                        .nickName(userDto.getNickName())
                        .build()
        );
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.load(PrefixUtil.withUserPrefix(email), "PROFILE")
                .map(user -> UserDto.builder()
                        .email(email)
                        .nickName(user.getNickName())
                        .build());
    }
}
