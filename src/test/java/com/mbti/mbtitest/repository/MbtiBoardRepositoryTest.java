package com.mbti.mbtitest.repository;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MbtiBoardRepositoryTest {

    @Autowired
    MbtiBoardRepository boardRepository;

    /*@After
    public void cleanup(){
        boardRepository.deleteAll();
    }*/

    @Test
    public void save(){
        // 1.given
        MbtiBoard params = MbtiBoard.builder()
                .userid("testKim")
                .mbti('E')
                .content("so fun people")
                .selectedkeyword('Y')
                .build();

        // 2.when
        boardRepository.save(params);

        // 3.then
        MbtiBoard entity = boardRepository.findById((long)1).get();
        assertThat(entity.getUserid()).isEqualTo("testKim");
        assertThat(entity.getMbti()).isEqualTo('E');
        assertThat(entity.getContent()).isEqualTo("so fun people");
        assertThat(entity.getSelectedkeyword()).isEqualTo('Y');


    }

    // 전체 게시글 수 조회
    @Test
    public void findAllCount(){
        // when
        long mbtiBoardsCount = boardRepository.count();

    }

    // 전체 게시글 리스트 조회
    @Test
    public void findAll(){

        // when
        List<MbtiBoard> mbtiBoards = boardRepository.findAll();
    }

    // 게시글 삭제
    @Test
    public void delete(){
        // 특정 게시글 조회
        MbtiBoard entity = boardRepository.findById((long)1).get();

        boardRepository.delete(entity);
    }

}
