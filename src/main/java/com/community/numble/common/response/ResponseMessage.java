package com.community.numble.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    AUTH_USER("사용자 정보가 없습니다.");
    
    final String message;
}
