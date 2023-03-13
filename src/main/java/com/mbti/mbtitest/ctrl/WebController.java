package com.mbti.mbtitest.ctrl;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // 사용 기술 페이지 이동
    @GetMapping("usedSkills")
    public String selectUsedSkills() {

        return "usedskills";
    }

}