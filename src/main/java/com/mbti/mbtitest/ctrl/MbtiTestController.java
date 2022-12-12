package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.dto.MbtiTestSelectRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("mbtiTest")
@Controller
public class MbtiTestController {

    // mbti 테스트 입력 화면 이동
    @GetMapping("")
    public ModelAndView goToTestPage(){

        ModelAndView mav = new ModelAndView();

        mav.setViewName("mbtitest");

        return mav;
    }

    // mbti 테스트 결과 화면 이동
    @GetMapping("/result")
    public ModelAndView goToTestResultPage(MbtiTestSelectRequestDto dto){

        ModelAndView mav = new ModelAndView();

        System.out.println("dto : " + dto.getMbti());

        mav.setViewName("mbtitestresult");

        return mav;
    }



}
