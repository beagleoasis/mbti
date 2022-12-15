package com.mbti.mbtitest.repository;

import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<MbtiBoard, Long> {

}
