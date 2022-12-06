package com.mbti.mbtitest.service;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.repository.MbtiBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MbtiBoardService {

    private MbtiBoardRepository mbtiBoardRepository;

    // 게시판 전체 조회
    public List<MbtiBoard> findAll(){
        return mbtiBoardRepository.findAll();
    }

}
