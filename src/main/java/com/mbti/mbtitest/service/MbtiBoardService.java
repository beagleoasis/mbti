package com.mbti.mbtitest.service;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.dto.MbtiBoardSaveRequestDto;
import com.mbti.mbtitest.repository.MbtiBoardRepository;
import lombok.AllArgsConstructor;
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
    public List<MbtiBoard> findAll(){
        return mbtiBoardRepository.findAll();
    }

}
