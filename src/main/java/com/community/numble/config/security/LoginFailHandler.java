package com.community.numble.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        log.info("BadCredentialsException : {}, Request IP is {}", exception.getMessage(), request.getRemoteAddr());
        setDefaultFailureUrl("/login?error=true");

        super.onAuthenticationFailure(request, response, exception);
    }
}
