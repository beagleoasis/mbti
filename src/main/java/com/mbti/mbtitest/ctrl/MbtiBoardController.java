package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.service.MbtiBoardService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
public class MbtiBoardController {

    private MbtiBoardService service;

    // 생성자 방식의 의존성 주입
    public MbtiBoardController(MbtiBoardService service){
        this.service = service;
    }

    @GetMapping("/mbtiBoards")
    public String selectAllMbtiBoards(){

        return "mbtiboard/mbtiboard";
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
