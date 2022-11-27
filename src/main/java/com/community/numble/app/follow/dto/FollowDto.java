package com.community.numble.app.follow.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FollowDto {

    private String nickname;

    private String imageCode;

}
