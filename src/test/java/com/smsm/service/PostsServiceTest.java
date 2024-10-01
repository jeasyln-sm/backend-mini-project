package com.smsm.service;

import com.smsm.dto.PostRequestDto;
import com.smsm.entity.Member;
import com.smsm.entity.Posts;
import com.smsm.repository.MemberRepository;
import com.smsm.repository.PostsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private MemberRepository memberRepository;
    

    @Test
    @DisplayName("게시글 작성")
    void createPosts() {

        // 1. Member 객체 생성
        Member member = Member.builder()
                .name("홍길동")
                .email("bWwZ8@example.com")
                .build();

        this.memberRepository.save(member);

        // 2. PostRequestDto 생성
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .title("제목1")
                .content("내용1")
                .noticeYn(false)
                .build();

        // 3. toEntity() 메소드를 통해 Posts 객체로 변환
        Posts posts = postRequestDto.toEntity(member);

        // 4. 게시글 저장
        this.postsRepository.save(posts);

        // 5. 저장된 게시글을 조회하여 검증
        Optional<Posts> savedPost = this.postsRepository.findById(posts.getId());
        assertTrue(savedPost.isPresent(), "게시글이 저장되지 않았습니다.");
        assertEquals(posts.getTitle(), savedPost.get().getTitle(), "제목이 일치하지 않습니다.");
        assertEquals(posts.getContent(), savedPost.get().getContent(), "내용이 일치하지 않습니다.");
    }
}
