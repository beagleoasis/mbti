package com.mbti.mbtitest.ctrl;


import com.mbti.mbtitest.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    private PostsService postsService;

    @GetMapping("usedSkills")
    public String selectUsedSkills() {

        return "usedSkills";
    }


}