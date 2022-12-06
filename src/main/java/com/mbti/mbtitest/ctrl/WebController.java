package com.mbti.mbtitest.ctrl;


import com.mbti.mbtitest.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("helloTest","Test!!");
        return "index";
    }

    @GetMapping("/mbti")
    public String selectMbti(Model model, HttpSession httpSession){
        model.addAttribute("userInfo", httpSession.getAttribute("user"));
        System.out.println("model : " + model.getAttribute("userInfo"));
        return "mbtiboard";
    }

    @GetMapping("/test")
    public String test(Model model, HttpSession httpSession) {

        model.addAttribute("userInfo", httpSession.getAttribute("user"));
        return "index";
    }

}