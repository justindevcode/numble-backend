package com.community.numble.system.email.dto;

import com.community.numble.common.utils.DateUtils;
import com.community.numble.system.email.domain.Email;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CheckEmailTokenDto {

    private String email;

    @NotNull
    private String token;

    public Email toEntity(){
        return Email.builder()
            .toEmail(email)
            .token(token)
            .sendDate(DateUtils.format(LocalDateTime.now(), "yyyyMMddHHmmss"))
            .tokenVerified("N")
            .build();

    }
}
