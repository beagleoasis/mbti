package com.mbti.mbtitest.config.auth;

import com.mbti.mbtitest.domain.user.User;
import lombok.Getter;

@Getter
public class SessionUser {

    private String name;
    private String email;

    private Role role;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
