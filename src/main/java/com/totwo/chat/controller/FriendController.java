package com.totwo.chat.controller;

import com.totwo.chat.common.CommonResponse;
import com.totwo.chat.dto.UserDto;
import com.totwo.chat.security.AuthUtils;
import com.totwo.chat.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/friend")
@RestController
public class FriendController {
    private final AuthUtils authUtils;
    private final FriendService friendService;

    @GetMapping("/list/get")
    public ResponseEntity<CommonResponse<List<UserDto>>> getFriendList() {
        String email = authUtils.getEmail();
        List<UserDto> userList = friendService.getFriends(email);
        log.info("[api /friend/list/get]: {}", userList);
        return CommonResponse.ok(userList);
    }

    @PostMapping("/private/add")
    public ResponseEntity<CommonResponse<UserDto>> addFriend(@RequestBody UserDto userDto) {
        String myEmail = authUtils.getEmail();
        String friendEmail = userDto.getEmail();
        friendService.registerFriend(myEmail, friendEmail);
        log.info("[api /friend/private/add]");
        return CommonResponse.created(null);
    }
}
