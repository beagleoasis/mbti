package com.mbti.mbtitest.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("mbtiTest")
@Controller
public class MbtiTestController {

    @GetMapping("")
    public ModelAndView goToTestPage(){

        ModelAndView mav = new ModelAndView();

        mav.setViewName("mbtitest");

        return mav;
    }

}
