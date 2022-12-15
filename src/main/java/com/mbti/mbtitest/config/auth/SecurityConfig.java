package com.mbti.mbtitest.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 시큐리티 관련 설정
@EnableWebSecurity // spring security 설정을 활성화
@RequiredArgsConstructor // final 필드 생성자
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;

    // filterChain 방식
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HttpSecurity Settings : 리소스(URL) 접근 권한 설정, 이동 지점 설정 등

        System.out.println("filterChain http : " + http.authorizeRequests());

        http.csrf().disable(); // csrf 공격을 막아주는 옵션을 해제, rest api의 경우 브라우저를 통해 request를 받지 않기 때문

        http
                .authorizeRequests() // 요청에 의한 보안 검사 시작,
			    .antMatchers("/admins/**").hasRole("ADMIN") // 특정 ROLE을 가진 사용자만 접근 가능하도록 설정
                //.antMatchers("/**").authenticated() // 인가된 사용자만 접근 가능하도록 설정
                //.anyRequest().authenticated() // 어느 요청에도 보안 검사를 하겠다.
                .anyRequest().permitAll()

                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                    .logoutSuccessUrl("/loginJoin")// 로그아웃 요청시, 홈으로 이동
                    .invalidateHttpSession(true) // 로그아웃시 생성된 세션 삭제 활성화

                .and()
                    .oauth2Login() // OAuth2 로그인을 처리하는 메서드
                    .defaultSuccessUrl("/mbtiBoards") // 로그인 성공 후 리디렉션 페이지
                    .failureUrl("/") // 로그인 실패 후 리디렉션 페이지
                    .userInfoEndpoint() // 로그인 성공 후, 사용자 정보를 가져올 때 설정을 담당
                    .userService(oAuth2UserService); // 로그인 성공 시, 작업을 진행


        System.out.println("http : " + http.toString());

        return http.build();
    }
}
