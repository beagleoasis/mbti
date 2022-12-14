package com.mbti.mbtitest.domain.user;

import com.mbti.mbtitest.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성
@Getter // Lombok getter 생성
@Entity // JPA Entity 명시
@Table(name = "member") // member 테이블
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "nickname", nullable = true, unique = true)
    private String nickname;


    @Builder //생성을 Builder 패턴으로 하기 위해서
    public User(Long id, String name, String email, String role, String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
    }

    //
    public User update(String name) {
        this.name = name;
        return this;
    }


}
