package com.community.numble.app.user.dto;

import com.community.numble.app.user.domain.*;
import lombok.*;

@Data
@Builder
public class UserDto {

    private String username;

    private String name;

    private String birthday;

    private String cellPhone;

    private String profileCode;

    private String address;

    private String introduce;

    public static UserDto of(User user) {

        return UserDto.builder()
                .introduce(user.getIntroduce())
                .address(user.getAddress())
                .build();

    }
}
