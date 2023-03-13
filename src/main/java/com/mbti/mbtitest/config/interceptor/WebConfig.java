package com.mbti.mbtitest.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(new LoginInterceptor()) // 로그인 인터셉터 등록
                .order(2) // 숫자가 낮을수록 먼저 호출
                .addPathPatterns("/**/post") // 인터셉터를 적용할 url 패턴
                .addPathPatterns("/**/revision")
                .addPathPatterns("/**/mypage")
                .addPathPatterns("/**/deletion")
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 인터셉터에서 제외할 패턴
    }
}
