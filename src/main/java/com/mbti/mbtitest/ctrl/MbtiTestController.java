package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiTestSelectRequestDto;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("mbtiTest")
@Controller
public class MbtiTestController {

    private MbtiBoardService mbtiBoardService;

    public MbtiTestController(MbtiBoardService mbtiBoardService){
        this.mbtiBoardService = mbtiBoardService;
    }


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

        List<MbtiBoard> mbtiBoards = mbtiBoardService.findRandomlySelectedMbtiBoards();

        mav.addObject("mbtiBoards",mbtiBoards);
        mav.setViewName("mbtitestresult");

        return mav;
    }



}
