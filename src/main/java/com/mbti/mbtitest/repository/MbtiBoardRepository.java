package com.mbti.mbtitest.repository;


import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiBoardRepository extends JpaRepository<MbtiBoard, Long> {


}
