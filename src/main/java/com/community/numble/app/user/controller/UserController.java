package com.community.numble.app.user.controller;

import com.community.numble.app.user.dto.UserCreateDto;
import com.community.numble.app.user.service.UserService;
import com.community.numble.common.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 관련 api")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseService responseService;

    @Operation(summary = "회원가입 메서드", description = "회원가입 메서드입니다.")
    @PostMapping("/join")
    public ResponseEntity<?> register(UserCreateDto userCreateDto){

        userService.join(userCreateDto);

        return ResponseEntity.ok(responseService.getSuccessResult());
    }

}
