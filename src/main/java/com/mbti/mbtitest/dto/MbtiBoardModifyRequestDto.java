package com.mbti.mbtitest.dto;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MbtiBoardModifyRequestDto {

    private char mbti;

    private String content;

    public MbtiBoard toEntity(){
        return MbtiBoard.builder()
                .mbti(mbti)
                .content(content)
                .build();
    }

    @Builder
    public MbtiBoardModifyRequestDto(char mbti, String content){
        this.mbti = mbti;
        this.content = content;
    }
}
