package com.mbti.mbtitest.config.auth;


public enum Role {
    ROLE_USER("ROLE_USER"), // 일반 유저
    ROLE_ANONYMOUS("ROLE_ANONYMOUS"), // 익명 유저
    ROLE_ADMIN("ROLE_ADMIN"); // 관리자

    String role;

    Role(String role){
        this.role = role;
    }

    public String value(){
        return role;
    }
}
