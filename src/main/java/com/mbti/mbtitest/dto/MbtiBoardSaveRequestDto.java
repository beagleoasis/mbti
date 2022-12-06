package com.mbti.mbtitest.dto;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MbtiBoardSaveRequestDto {

    private String userid;

    private char mbti;

    private String content;

    private char selectedkeyword;

    public MbtiBoard toEntity(){

        return MbtiBoard.builder()
                .userid(userid)
                .mbti(mbti)
                .content(content)
                .selectedkeyword(selectedkeyword)
                .build();
    }


}
