package com.mbti.mbtitest.ctrl;


import com.mbti.mbtitest.service.PostsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String selectMbti(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }
}