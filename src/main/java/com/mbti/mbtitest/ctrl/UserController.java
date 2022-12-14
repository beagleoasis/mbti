package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.config.auth.SessionUser;
import com.mbti.mbtitest.domain.user.User;
import com.mbti.mbtitest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

        User user = userService.findOneByEmail(email);

        System.out.println("user : " + user.getId());

        mav.addObject("user", user);

        mav.setViewName("mypage");

        return mav;
    }

    // 유저 삭제
    @DeleteMapping("/mypage/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){

        System.out.println("id : " + id);

        userService.deleteUser(id);

        return ResponseEntity.ok(200);
    }

}
