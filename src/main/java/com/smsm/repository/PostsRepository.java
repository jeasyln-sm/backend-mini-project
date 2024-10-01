package com.smsm.repository;

import com.smsm.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByNoticeYn(Boolean noticeYn); // 공지글 조회
}
