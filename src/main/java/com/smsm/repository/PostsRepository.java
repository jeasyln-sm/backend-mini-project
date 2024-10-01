package com.smsm.repository;

import com.smsm.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByNoticeYn(Boolean noticeYn); // 공지글 조회

    @Query("SELECT p FROM Posts p JOIN FETCH p.member")
    Page<Posts> findAllWithMember(Pageable pageable);

    @Query("SELECT p FROM Posts p ORDER BY p.noticeYn DESC, p.creDate DESC")
    Page<Posts> findAllOrderByNoticeYnAndCreDate(Pageable pageable);
}
