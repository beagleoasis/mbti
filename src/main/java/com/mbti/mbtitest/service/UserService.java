package com.mbti.mbtitest.service;

import com.mbti.mbtitest.domain.user.User;
import com.mbti.mbtitest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    // 회원가입 유저 이메일로 정보 조회
    @Transactional
    public User findOneByEmail(String email){

        return userRepository.findUserByEmail(email);
    }

}
