package com.community.numble.app.follow.service;

import com.community.numble.app.follow.domain.Follow;
import com.community.numble.app.follow.dto.FollowDto;
import com.community.numble.app.follow.repository.FollowRepository;
import com.community.numble.app.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    public List<FollowDto> getFollowList(Long userId) {

        List<Follow> followList = followRepository.findFollowsByUserId(userId);
        List<Long> followUserIdList = new ArrayList<>();
        followList.forEach(follow -> {
            followUserIdList.add(follow.getUserId());
        });
        List<FollowDto> result = new ArrayList<>();
        userRepository.findUserBy(followUserIdList).forEach(user -> {
            FollowDto followDto = FollowDto.builder().nickname(user.getNickname()).imageCode(user.getImageCode()).build();
            result.add(followDto);
        });

        return result;

    }

}
