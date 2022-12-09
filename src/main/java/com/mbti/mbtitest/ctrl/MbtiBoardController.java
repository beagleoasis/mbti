package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.config.auth.SessionUser;
import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiBoardSaveRequestDto;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MbtiBoardController {

    private MbtiBoardService mbtiBoardService;

    // 생성자 방식의 의존성 주입
    public MbtiBoardController(MbtiBoardService mbtiBoardService){
        this.mbtiBoardService = mbtiBoardService;
    }

    // 게시글 조회
    @GetMapping("/mbtiBoards")
    public String selectAllMbtiBoards(Model model){

        List<MbtiBoard> mbtiBoards = mbtiBoardService.findAll();

        model.addAttribute("mbtiBoards", mbtiBoards);

        return "mbtiboard";
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/mbtiBoards/write")
    public String selectMbtiBoardWrite(){

        return "mbtiboardwrite";
    }

    // 글 작성 완료
    @PostMapping("/mbtiBoards/write")
    public String insertMbtiBoardWrite(HttpSession session, MbtiBoardSaveRequestDto dto, Model model){

            SessionUser user;

            if(session.getAttribute("user") != null){
                user = (SessionUser) session.getAttribute("user");
                dto.setUserid(user.getName());
            }
            else{
                model.addAttribute("result", 0);
                return "mbtiboard";
            }
            System.out.println("dto : " + dto.getContent());

            Long result = mbtiBoardService.save(dto);
            model.addAttribute("result", result);

        return "redirect:";
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
