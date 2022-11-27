package com.community.numble.app.follow.controller;

import com.community.numble.app.follow.dto.FollowDto;
import com.community.numble.app.follow.service.FollowService;
import com.community.numble.app.user.domain.User;
import com.community.numble.app.user.service.UserService;
import com.community.numble.common.service.ResponseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class FollowController {

    private final ResponseService responseService;

    private final FollowService followService;

    private final UserService userService;

    @GetMapping("/follow/list")
    public ResponseEntity<?> followList(@AuthenticationPrincipal User user){

        List<FollowDto> result = followService.getFollowList(user.getUserId());

        return ResponseEntity.ok(responseService.getListResult(result));

    }

    @GetMapping("/follower/list")
    public ResponseEntity<?> followerList(@AuthenticationPrincipal User user){

        followService.getFollowerList(user.getUserId());
        return ResponseEntity.ok(responseService.getSingleResult());
    }

    @PostMapping("/follow/insert")
    public ResponseEntity<?> insertFollower(FollowDto followDto){

        followService.insertFollow(followDto);
        return ResponseEntity.ok(responseService.getSuccessResult());

    }

    @PostMapping("/follow/delete")
    public ResponseEntity<?> deleteFollow(@AuthenticationPrincipal User user){

        return ResponseEntity.ok(responseService.getSuccessResult());

    }


}
