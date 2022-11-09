package com.community.numble.app.user.controller;

import com.community.numble.app.user.dto.UserCreateDto;
import com.community.numble.app.user.service.UserService;
import com.community.numble.common.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseService responseService;


    @PostMapping("/join")
    public ResponseEntity<?> register(UserCreateDto userCreateDto){

        userService.join(userCreateDto);

        return ResponseEntity.ok(responseService.getSuccessResult());
    }

}
