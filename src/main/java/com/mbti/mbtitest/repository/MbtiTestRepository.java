package com.mbti.mbtitest.repository;


import com.mbti.mbtitest.domain.mbtiboard.MbtiBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MbtiTestRepository extends JpaRepository<MbtiBoard, Long> {

    // 관리자에 의해 선택된 게시글 랜덤 추출 쿼리
    @Query(value = "SELECT * " +
            "FROM mbtitest.mbti_board " +
            "where selectedkeyword = 'T'" +
            "and status is null " +
            "and mbti = :mbti"
                , nativeQuery = true)
    List<MbtiBoard> findAllByMbti(@Param("mbti") String mbti);

}
