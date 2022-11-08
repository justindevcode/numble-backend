package com.community.numble.app.user.service;

import com.community.numble.app.user.bean.UserBean;
import com.community.numble.app.user.repository.UserRepository;
import com.community.numble.common.response.ResponseMessage;
import com.community.numble.exception.LoginFailedException;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserBean userBean = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(userBean)) {
            throw new LoginFailedException(ResponseMessage.AUTH_USER.getMessage());
        }

        final Collection<GrantedAuthority> authorities = userBean.getRole().stream().map(
            role -> new SimpleGrantedAuthority(role.getRole()))
            .collect(Collectors.toList());

        userBean.setAuthorities(authorities);

        return userBean;
    }
}
