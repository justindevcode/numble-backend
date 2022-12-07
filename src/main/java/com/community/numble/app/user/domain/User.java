package com.community.numble.app.user.domain;

import com.community.numble.app.board.domain.*;
import com.community.numble.app.follow.domain.*;
import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import javax.persistence.Transient;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Builder
@Getter
@Table(name="USER_TABLE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Email
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String birthday;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> role;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String cellPhone;

    @Column
    private String imageCode;

    private int score;

    private String createDate;

    private String updateDate;

    private String emailCheckToken;

    private String introduce;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Board> boardList;

    @OneToMany
    private List<Follow> followList;

    @Transient
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    public void setRole(List<Role> role){
        this.role = role;
    }

}
