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

@RequestMapping("mbtiBoards")
@Controller
public class MbtiBoardController {
    // 로거
    private static final Logger logger = LoggerFactory.getLogger(MbtiBoardController.class);

    private MbtiBoardService mbtiBoardService;

    // 생성자 방식의 의존성 주입
    public MbtiBoardController(MbtiBoardService mbtiBoardService){
        this.mbtiBoardService = mbtiBoardService;
    }


    // 게시글 목록 조회
    @GetMapping("")
    public ModelAndView selectAllMbtiBoards(HttpServletRequest request, HttpServletResponse response,
                                            // 페이징을 위한 PageableDefault 매개변수 추가
                                            @PageableDefault(page = 0, size = 10, sort = "boardno", direction = Sort.Direction.DESC)
                                            Pageable pageRequest){

        // 유저 세션
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        // 데이터를 담아 view로 전달되는 model and view
        ModelAndView mav = new ModelAndView();

        if(sessionUser!=null){
            mav.addObject("userInfo", sessionUser);
        }

        Page<MbtiBoard> mbtiBoards = mbtiBoardService.findAllExceptForDelete(pageRequest);

        mav.addObject("mbtiBoards", mbtiBoards);
        mav.setViewName("mbtiboard");

        return mav;
    }

    // 게시글 작성 페이지 이동
    @GetMapping("post")
    public ModelAndView selectMbtiBoardWrite(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mav = new ModelAndView();

        mav.setViewName("mbtiboardwrite");

        return mav;
    }

    // 글 작성 완료
    @PostMapping("post")
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

            dto.setUseremail(user.getEmail());

            Long result = mbtiBoardService.save(dto);

            mav.addObject("result", result);


            mav.setView(redirectView);

        return mav;
    }

    // 게시글 수정 페이지 이동
    @PostMapping("revision")
    public ModelAndView selectMbtiBoardModify(HttpServletRequest request, HttpServletResponse response){

        Long id = Long.valueOf(request.getParameter("boardno"));

        ModelAndView mav = new ModelAndView();

        MbtiBoard mbtiBoard = mbtiBoardService.findOneById(id);

        mav.addObject("mbtiBoard", mbtiBoard);

        mav.setViewName("mbtiboardmodify");

        return mav;
    }

    // 게시글 수정
    @PutMapping("revision/{boardno}")
    public ResponseEntity updateMbtiBoardModify(@PathVariable Long boardno, HttpServletRequest request, HttpServletResponse response, @RequestBody MbtiBoardModifyRequestDto dto){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        long result = 0;

        if(sessionUser==null){
            return ResponseEntity.ok(result);
        }

        result = mbtiBoardService.update(boardno, dto);

        return ResponseEntity.ok(result);
    }


    // 게시글 삭제
    @DeleteMapping("deletion/{boardno}")
    public ResponseEntity deleteMbtiBoard(@PathVariable Long boardno, HttpServletRequest request, HttpServletResponse response){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        long result = 0;

        if(sessionUser==null){
            return ResponseEntity.ok(result);
        }

        result = mbtiBoardService.delete(boardno);

        return ResponseEntity.ok(result);
    }


}
