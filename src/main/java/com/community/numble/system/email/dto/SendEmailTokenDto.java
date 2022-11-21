package com.community.numble.system.email.dto;

import com.community.numble.system.email.domain.Email;
import lombok.Data;

@Data
public class SendEmailTokenDto {

    private String toEmail;

    public Email toEntity() {

        return Email.builder().toEmail(toEmail).build();
    }
}
