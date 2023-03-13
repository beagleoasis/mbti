package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.service.LoginJoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginJoinController {

    private LoginJoinService loginJoinService;

    // 생성자 방식의 의존성 주입
    public  LoginJoinController(LoginJoinService loginJoinService){
        this.loginJoinService = loginJoinService;
    }

    // 로그인/회원가입 페이지 이동
    @GetMapping("loginJoin")
    public String goToLoginJoinPage(Model model){

        return "loginjoin";
    }

}
