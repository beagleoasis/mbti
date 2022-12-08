package com.mbti.mbtitest.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);

        if(session==null || session.getAttribute("user")==null){
            System.out.println("비로그인 유저");
            return false;
        }

        return true;
    }

}
