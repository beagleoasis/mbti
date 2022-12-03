package com.mbti.mbtitest.domain.member;

import com.mbti.mbtitest.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long userno;

    @Column
    private String userid;

    @Column
    private String userpw;

    @Column
    private char status;


}
