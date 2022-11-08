package com.community.numble.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginFailedException extends RuntimeException{

    private final String message;
}
