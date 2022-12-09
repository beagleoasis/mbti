package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.config.auth.SessionUser;
import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiBoardSaveRequestDto;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MbtiBoardController {
    // 로거
    private static final Logger logger = LoggerFactory.getLogger(MbtiBoardController.class);

    private MbtiBoardService mbtiBoardService;

    // 생성자 방식의 의존성 주입
    public MbtiBoardController(MbtiBoardService mbtiBoardService){
        this.mbtiBoardService = mbtiBoardService;
    }


    // 게시글 조회
    @GetMapping("/mbtiBoards")
    public ModelAndView selectAllMbtiBoards(HttpServletRequest request, HttpServletResponse response){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        ModelAndView mav = new ModelAndView();

        if(sessionUser!=null){
            mav.addObject("userInfo", sessionUser);
        }


        List<MbtiBoard> mbtiBoards = mbtiBoardService.findAll();

        mav.addObject("mbtiBoards", mbtiBoards);
        mav.setViewName("mbtiBoard");

        logger.debug("mbtiBoards 유저 로그인 확인 : " + mav);
        System.out.println("mbtiBoards 유저 로그인 확인 : " + mav);


        return mav;
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/mbtiBoards/write")
    public ModelAndView selectMbtiBoardWrite(ModelAndView mav){

        mav.setViewName("mbtiboardwrite");

        return mav;
    }

    // 글 작성 완료
    @PostMapping("/mbtiBoards/write")
    public ModelAndView insertMbtiBoardWrite(HttpSession session, MbtiBoardSaveRequestDto dto, ModelAndView mav){


            SessionUser user;
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/mbtiBoards");

            if(session.getAttribute("user") != null){
                user = (SessionUser) session.getAttribute("user");
                dto.setUserid(user.getName());
            }
            else{
                mav.addObject("result",0);
                return mav;
            }
            System.out.println("dto : " + dto.getContent());

            Long result = mbtiBoardService.save(dto);

            System.out.println("result : " + result);

            mav.addObject("result", result);


            mav.setView(redirectView);

        return mav;
    }


    /*
    // 모든 mbti 게시글 조회
    @GetMapping("/mbtiBoards")
    public ResponseEntity<CollectionModel<EntityModel<MbtiBoard>>> selectAllMbtiBoards(){

        List<EntityModel<MbtiBoard>> result = new ArrayList<>();

        List<MbtiBoard> mbtiBoards = service.findAll();

        for (MbtiBoard mbtiBoard : mbtiBoards){
            EntityModel entityModel = EntityModel.of(mbtiBoard);
            entityModel.add(linkTo(methodOn(this.getClass()).selectAllMbtiBoards()).withSelfRel());

            result.add(entityModel);
        }

        return ResponseEntity.ok(
                CollectionModel.of(
                        result,linkTo(
                                methodOn(
                                this.getClass()
                                ).selectAllMbtiBoards()
                        ).withSelfRel()
                    )
                );

    }
    */





}
