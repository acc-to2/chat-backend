package com.totwo.chat.service.impl;

import com.totwo.chat.dto.UserDto;
import com.totwo.chat.entity.Friend;
import com.totwo.chat.repository.FriendRepository;
import com.totwo.chat.repository.UserRepository;
import com.totwo.chat.service.FriendService;
import com.totwo.chat.service.util.PrefixUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Override
    public void registerFriend(String userEmail, String friendEmail) {
        userRepository.load(PrefixUtil.withUserPrefix(friendEmail), "PROFILE")
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + friendEmail));

        friendRepository.save(Friend.builder()
                .pk(PrefixUtil.withUserPrefix(userEmail))
                .sk(PrefixUtil.withUserPrefix(friendEmail))
                .build());
    }

    @Override
    public void deleteFriend(String userEmail, String friendEmail) {
        friendRepository.delete(PrefixUtil.withUserPrefix(userEmail), PrefixUtil.withUserPrefix(friendEmail));
    }

    @Override
    public List<UserDto> getFriends(String userEmail) {
        return friendRepository.loadSkBeginsWith(PrefixUtil.withUserPrefix(userEmail), PrefixUtil.USER_PREFIX)
                .stream()
                .map(friend -> userRepository.load(friend.getSk(), "PROFILE")
                        .map(user -> UserDto.builder()
                                .email(PrefixUtil.removeUserPrefix(user.getPk()))
                                .nickName(user.getNickName())
                                .build())
                        .orElseGet(() -> {
                            deleteFriend(userEmail, PrefixUtil.removeUserPrefix(friend.getSk()));
                            return null;
                        }))
                .filter(Objects::nonNull)
                .toList();
    }
}
