package com.mbti.mbtitest.service;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import com.mbti.mbtitest.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminService {

    private AdminRepository adminRepository;

    // mbtiBoards 게시글 삭제 처리
/*    @Transactional
    public Long deleteMbtiBoard(Long boardno){
        MbtiBoard mbtiBoard = adminRepository.findById(boardno)
                .orElseThrow(IllegalArgumentException::new);

        mbtiBoard.delete("T");

        return boardno;
    }*/

    // mbtiBoards 키워드 선택/해제
    @Transactional
    public Long updateMbtiBoardKeyword(Long boardno){

        MbtiBoard mbtiBoard = adminRepository.findById(boardno)
                .orElseThrow(IllegalArgumentException::new);

        String checkKeyword = "T";

        if(mbtiBoard.getSelectedkeyword() == null || mbtiBoard.getSelectedkeyword().equals("T")){
            checkKeyword = "F";
        }

        mbtiBoard.updateKeyword(checkKeyword);

        return boardno;
    }

}
