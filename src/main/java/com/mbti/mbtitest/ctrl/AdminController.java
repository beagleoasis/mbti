package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.config.auth.SessionUser;
import com.mbti.mbtitest.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/admins")
@Controller
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // mbtiBoards 게시글 삭제
/*    @DeleteMapping("/mbtiBoards/{boardno}")
    public ResponseEntity deleteMbtiBoard(@PathVariable Long boardno, HttpServletRequest request, HttpServletResponse response){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        long result = 0;

        if(sessionUser==null){
            return ResponseEntity.ok(result);
        }

        result = adminService.deleteMbtiBoard(boardno);

        return ResponseEntity.ok(result);
    }*/


    // mbtiBoards 게시글 키워드 등록/해제
    @PutMapping("mbtiBoards/{boardno}")
    public ResponseEntity updateMbtiBoardKeyword(@PathVariable Long boardno, HttpServletRequest request, HttpServletResponse response){

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        long result = 0;

        if(sessionUser==null){
            return ResponseEntity.ok(result);
        }

        result = adminService.updateMbtiBoardKeyword(boardno);

        System.out.println("result 확인 : " + result);
        return ResponseEntity.ok(result);
    }

}
