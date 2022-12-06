package com.mbti.mbtitest.repository;


import com.mbti.mbtitest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginJoinRepository extends JpaRepository<User, Long> {


}
