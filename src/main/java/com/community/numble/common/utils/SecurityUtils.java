package com.community.numble.common.utils;

import com.community.numble.app.user.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils(){
        throw new RuntimeException("생성자 금지");
    }

    public static User getUser(){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("인증 정보가 없습니다.");
        }

        return (User)authentication.getPrincipal();

    }
}
