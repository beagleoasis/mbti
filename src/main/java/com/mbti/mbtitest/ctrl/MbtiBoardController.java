package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.config.auth.SessionUser;
import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiBoardModifyRequestDto;
import com.mbti.mbtitest.dto.MbtiBoardSaveRequestDto;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/mbtiBoards")
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
    @GetMapping("")
    public ModelAndView selectAllMbtiBoards(HttpServletRequest request, HttpServletResponse response,
                                            // 페이징을 위한 PageableDefault 매개변수 추가
                                            @PageableDefault(page = 0, size = 10, sort = "boardno", direction = Sort.Direction.DESC)
                                            Pageable pageRequest){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        ModelAndView mav = new ModelAndView();

        if(sessionUser!=null){
            mav.addObject("userInfo", sessionUser);
        }

        Page<MbtiBoard> mbtiBoards = mbtiBoardService.findAll(pageRequest);

        mav.addObject("mbtiBoards", mbtiBoards);
        mav.setViewName("mbtiBoard");

        logger.debug("mbtiBoards 유저 로그인 확인 : " + mav);
        System.out.println("mbtiBoards 유저 로그인 확인 : " + mav);
        System.out.println("mbtiBoards 유저 로그인 확인 : " + mav.getModel().get("mbtiBoards"));
        System.out.println("mbtiBoards 유저 로그인 확인 : " + mav.getModel().get("mbtiBoards").toString());
        System.out.println("mbtiBoards 유저 로그인 확인 : " + mav.getModel());



        return mav;
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/write")
    public ModelAndView selectMbtiBoardWrite(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mav = new ModelAndView();

        mav.setViewName("mbtiboardwrite");

        return mav;
    }

    // 글 작성 완료
    @PostMapping("/write")
    public ModelAndView insertMbtiBoardWrite(HttpServletRequest request, HttpServletResponse response, MbtiBoardSaveRequestDto dto){


            SessionUser user;
            ModelAndView mav = new ModelAndView();

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/mbtiBoards");

            if(request.getSession().getAttribute("user") != null){
                user = (SessionUser) request.getSession().getAttribute("user");
                dto.setUserid(user.getName());
            }
            else{
                mav.addObject("result",0);
                return mav;
            }
            System.out.println("dto : " + dto.getContent());

            dto.setUseremail(user.getEmail());

            Long result = mbtiBoardService.save(dto);

            System.out.println("result : " + result);

            mav.addObject("result", result);


            mav.setView(redirectView);

        return mav;
    }

    // 게시글 수정 페이지 이동
    @PostMapping("/modify")
    public ModelAndView selectMbtiBoardModify(HttpServletRequest request, HttpServletResponse response){

        System.out.println("게시글 수정 페이지 진입");

        Long id = Long.valueOf(request.getParameter("boardno"));

        System.out.println(id);

        ModelAndView mav = new ModelAndView();

        MbtiBoard mbtiBoard = mbtiBoardService.findOneById(id);

        mav.addObject("mbtiBoard", mbtiBoard);

        mav.setViewName("mbtiboardmodify");

        return mav;
    }

    // 게시글 수정
    @PutMapping("/modify/{boardno}")
    public ResponseEntity updateMbtiBoardModify(@PathVariable Long boardno, HttpServletRequest request, HttpServletResponse response, @RequestBody MbtiBoardModifyRequestDto dto){


        long result = 0;
        result = mbtiBoardService.update(boardno, dto);

        System.out.println("modify result 확인 : " + result);

        return ResponseEntity.ok(result);
    }


    // 게시글 삭제
    @DeleteMapping("/delete/{boardno}")
    public ResponseEntity deleteMbtiBoard(@PathVariable Long boardno){

        long result = mbtiBoardService.delete(boardno);

        return ResponseEntity.ok(result);
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
