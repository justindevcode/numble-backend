package com.community.numble.app.user.dto;

import com.community.numble.app.user.domain.*;
import lombok.*;


@Data
public class UserUpdateDto {

    private long id;

    private String nickname;

    private String cellPhone;

    private String address;

    public static User toEntity(UserUpdateDto userUpdateDto) {

        return User.builder()
                .userId(userUpdateDto.getId())
                .nickname(userUpdateDto.getNickname())
                .cellPhone(userUpdateDto.getCellPhone())
                .address(userUpdateDto.getAddress())
                .build();


    }
}
