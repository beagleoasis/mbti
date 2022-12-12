package com.mbti.mbtitest.service;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiBoardModifyRequestDto;
import com.mbti.mbtitest.dto.MbtiBoardSaveRequestDto;
import com.mbti.mbtitest.repository.MbtiBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class MbtiBoardService {

    private MbtiBoardRepository mbtiBoardRepository;

    // 게시글 저장
    @Transactional
    public Long save(MbtiBoardSaveRequestDto dto){
        return mbtiBoardRepository.save(dto.toEntity()).getBoardno();
    }

     // 게시판 전체 조회
     @Transactional
     public Page<MbtiBoard> findAll(Pageable pageable){
        //return mbtiBoardRepository.findAll();
        /*return mbtiBoardRepository.findAll(pageable);*/
         return mbtiBoardRepository.findMbtiBoardsByStatusIsNull(pageable);
    }

    // 게시글 수정을 위한 단일 게시글 조회
    @Transactional
    public MbtiBoard findOneById(long boardno){
        return mbtiBoardRepository.findById(boardno).get();
    }

    // 게시글 수정
    @Transactional
    public Long update(Long boardno, MbtiBoardModifyRequestDto dto){

        MbtiBoard mbtiBoard = mbtiBoardRepository.findById(boardno)
                .orElseThrow(IllegalArgumentException::new);

        // JPA의 영속성 컨텍스트 덕분에 entity 객체의 값만 변경하면, 자동으로 변경사항이 반영된다.
        mbtiBoard.update(dto.getMbti(), dto.getContent());

        return boardno;
    }

    // 게시글 삭제
    @Transactional
    public Long delete(Long boardno){

        MbtiBoard mbtiBoard = mbtiBoardRepository.findById(boardno)
                .orElseThrow(IllegalArgumentException::new);

        mbtiBoard.delete("T");

        return boardno;
    }



}
