package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.config.auth.SessionUser;
import com.mbti.mbtitest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/users")
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    // 유저 마이페이지 이동
    @GetMapping("/mypage")
    public ModelAndView selectUserMyPage(HttpServletRequest request, HttpServletResponse response){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        ModelAndView mav = new ModelAndView();

        if(sessionUser!=null){
            mav.addObject("userInfo", sessionUser);
        }
        else {
            mav.setViewName("loginjoin");
            return mav;
        }

        String email = sessionUser.getEmail();

        userService.findOneByEmail(email);

        mav.setViewName("mypage");

        return mav;
    }


}
