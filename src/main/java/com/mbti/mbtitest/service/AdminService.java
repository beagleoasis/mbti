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

        // null이 아니라면,
        if(mbtiBoard.getSelectedkeyword()!=null){
            // 키워드가 등록되어있다면,
            if(mbtiBoard.getSelectedkeyword().equals("T")){
                // 미등록 처리
                checkKeyword = "F";
            }
        }

        mbtiBoard.updateKeyword(checkKeyword);

        return boardno;
    }

}
