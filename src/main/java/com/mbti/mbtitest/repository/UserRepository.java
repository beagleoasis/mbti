package com.mbti.mbtitest.repository;

import com.mbti.mbtitest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 회원가입된 유저 이메일 정보로 찾기
    User findUserByEmail(String email);
}
