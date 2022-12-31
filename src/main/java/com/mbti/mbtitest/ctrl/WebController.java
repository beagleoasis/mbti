package com.mbti.mbtitest.ctrl;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("usedSkills")
    public String selectUsedSkills() {

        return "usedskills";
    }

}