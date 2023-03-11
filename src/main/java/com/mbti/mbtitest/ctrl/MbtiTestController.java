package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiTestSelectRequestDto;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    @GetMapping("result")
    public ModelAndView goToTestResultPage(MbtiTestSelectRequestDto dto){

        ModelAndView mav = new ModelAndView();

        System.out.println("dto : " + dto.getMbti());

        // dto를 통해 넘어온 mbti를 4가지 문자로 자르기
        String[] mbtiArr = dto.getMbti().toUpperCase().split("");

        List<List <MbtiBoard>> mbtiResultList = new ArrayList<>();

        for(int i=0; i<mbtiArr.length; i++){

            // mbti 별 개별 리스트 가져오기,
            List<MbtiBoard> mbtiBoards = mbtiBoardService.findRandomlySelectedMbtiBoards(mbtiArr[i]);

            // 전체 리스트에 추가
            mbtiResultList.add(mbtiBoards);
        }

        // null 처리 및 컨트롤러에서 타임리프 분류 작업 시작

        mav.addObject("mbtiResultList",mbtiResultList);

        mav.setViewName("mbtitestresult");

        return mav;
    }



}
