package com.mbti.mbtitest.dto;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MbtiBoardSaveRequestDto {

    // 게시글 작성자 아이디
    private String userid;

    // 게시글 작성자 이메일
    private String useremail;

    // mbti 유형
    private String mbti;

    // 작성된 키워드
    private String content;

    public MbtiBoard toEntity(){

        return MbtiBoard.builder()
                .userid(userid)
                .useremail(useremail)
                .mbti(mbti)
                .content(content)
                .build();
    }

    @Builder
    public MbtiBoardSaveRequestDto(String userid, String useremail, String mbti, String content){

        this.userid = userid;
        this.useremail = useremail;
        this.mbti = mbti;
        this.content = content;
    }

}
