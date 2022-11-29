package com.community.numble.app.user.dto;

import com.community.numble.app.user.domain.User;
import com.community.numble.common.*;
import com.community.numble.common.utils.DateUtils;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserCreateDto {

    private String name;
    private String username; // 이메일 계정

    private String nickname;

    private String birthday;

    private String password;

    private String passwordConfirm;

    private String cellPhone;

    private String address;

    public User toEntity() {

        return User.builder()
            .username(username)
            .birthday(birthday)
            .nickname(nickname)
            .password(password)
            .cellPhone(cellPhone)
            .address(address)
            .createDate(DateUtils.format(LocalDateTime.now(), Constants.DATE_FORMAT_YMDHMS))
            .build();

    }
}
