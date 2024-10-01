package com.smsm.service;

import com.smsm.dto.PostRequestDto;
import com.smsm.dto.PostResponseDto;
import com.smsm.entity.Member;
import com.smsm.entity.Posts;
import com.smsm.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 목록 조회
    public Page<PostResponseDto> getPosts(Pageable pageable) {
        return postsRepository.findAllOrderByNoticeYnAndCreDate(pageable)
            .map(PostResponseDto::fromEntity);
    }

    // 게시글 등록
    public void savePost(PostRequestDto postDto, Member member) {
        Posts posts = postDto.toEntity(member);
        postsRepository.save(posts);
    }
}
