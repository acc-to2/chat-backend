package com.totwo.chat.service;

import com.totwo.chat.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{
    @Override
    public void registerFriend(String userEmail, String friendEmail) {

    }

    @Override
    public void deleteFriend(String userEmail, String friendEmail) {

    }

    @Override
    public List<UserDto> getFriends(String userEmail) {
        return List.of();
    }
}
