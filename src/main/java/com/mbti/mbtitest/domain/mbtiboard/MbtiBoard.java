package com.mbti.mbtitest.domain.mbtiboard;

import com.mbti.mbtitest.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MbtiBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardno;

    @Column(length = 20,nullable = false)
    private String userid;

    @Column(nullable = false)
    private String useremail;

    @Column(nullable = false)
    private String mbti;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(length = 1)
    private String selectedkeyword;

    @Column(length = 1)
    private String status;

    @Builder
    public MbtiBoard(String userid, String useremail, String mbti, String content, String selectedkeyword){

        this.userid = userid;
        this.useremail = useremail;
        this.mbti = mbti;
        this.content = content;
        this.selectedkeyword = selectedkeyword;
    }

    public void update(String mbti, String content){
        this.mbti = mbti;
        this.content = content;
    }

    public void delete(String status){
        this.status = status;
    }
}
