package com.mbti.mbtitest.repository;


import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiBoardRepository extends JpaRepository<MbtiBoard, Long> {

    // 페이징 기법을 활용할 레포지토리에 메서드 추가
    Page<MbtiBoard> findAll(Pageable pageable);

}
