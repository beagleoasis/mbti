package com.mbti.mbtitest.config.auth;

import com.mbti.mbtitest.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

// OAuth2UserService를 통해 가져온 카카오 OAuth2User의 attributes를 담을 클래스
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes; // OAuth service 반환 유저 정보들
    private String nameAttributeKey;
    private String name;
    private String email;

    // 생성자
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes){
        // 카카오인 경우,
        if("kakao".equals(socialName)){
            return ofKakao("id", attributes);
        }

        // 네이버인 경우,

        return null;
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // 카카오 계정(email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // 카카오 프로필(nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.ROLE_USER) // 기본 권한을 ROLE_USER로 부여
                .build();
    }
}