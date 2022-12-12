package com.mbti.mbtitest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class MbtiTestSelectRequestDto {

    private String mbti;

    @Builder
    public MbtiTestSelectRequestDto(String mbti){
        this.mbti = mbti;
    }

}
