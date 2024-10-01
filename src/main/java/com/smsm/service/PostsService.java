package com.smsm.service;

import com.smsm.dto.PostResponseDto;
import com.smsm.entity.Posts;
import com.smsm.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 목록 조회
    public Page<PostResponseDto> getPosts(Pageable pageable) {
        Page<Posts> postsPage = postsRepository.findAllWithMember(pageable); // 수정된 메소드 호출
        return postsPage.map(PostResponseDto::fromEntity); // DTO 변환
    }
}
