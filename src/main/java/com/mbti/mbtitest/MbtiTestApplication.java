package com.mbti.mbtitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동
@SpringBootApplication
public class MbtiTestApplication {

    public static void main(String[] args) {
        // SpringApplication.run으로 WAS(Tomcat) 서버 실행
        SpringApplication.run(MbtiTestApplication.class, args);

    }

}
