package com.mbti.mbtitest.domain.mbtiboard;

import com.mbti.mbtitest.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MbtiBoard extends BaseTimeEntity {

    @Id
    private Long boardno;

    @Column
    private String userid;

    @Column
    private char mbti;

    @Column
    private String content;

    @Column
    private char selectedkeyword;

    @Column
    private char status;

}
