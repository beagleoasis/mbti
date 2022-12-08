package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
