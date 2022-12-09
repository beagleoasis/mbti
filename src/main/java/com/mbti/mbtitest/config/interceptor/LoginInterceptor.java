package com.mbti.mbtitest.config.interceptor;

import com.mbti.mbtitest.ctrl.MbtiBoardController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LoginInterceptor implements HandlerInterceptor {

    // 로거
    private static final Logger logger = LoggerFactory.getLogger(MbtiBoardController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        HttpSession session = request.getSession(false);

        if(session==null || session.getAttribute("user")==null){
            System.out.println("비로그인 유저");
            response.sendRedirect("/loginJoin");
            return false;
        }

        return true;
    }

}
