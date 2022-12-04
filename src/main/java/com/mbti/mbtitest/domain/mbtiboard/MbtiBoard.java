package com.mbti.mbtitest.domain.mbtiboard;

import com.mbti.mbtitest.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class MbtiBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long boardno;

    @Column(length = 20,nullable = false)
    private String userid;

    @Column(nullable = false)
    private char mbti;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column
    private char selectedkeyword;

    @Column
    private char status;

}
