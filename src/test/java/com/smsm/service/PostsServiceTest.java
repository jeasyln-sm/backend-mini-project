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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @DisplayName("게시글 작성")
    void createPosts() {

        // 1. Member 객체 생성
        Member member = Member.builder()
                .name("나길동")
                .email("hhobWwZ81132@example.com")
                .build();

        this.memberRepository.save(member);

        // 2. PostRequestDto 생성
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .title("제목12")
                .content("내용12")
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

        // 6. 작성자 정보 검증
        assertNotNull(savedPost.get().getMember(), "작성자 정보가 존재하지 않습니다.");
        assertEquals("나길동", savedPost.get().getMember().getName(), "작성자 이름이 일치하지 않습니다.");

        // 7. 작성일자 검증
        assertNotNull(savedPost.get().getCreDate(), "작성일자가 존재하지 않습니다.");
        // 작성일자가 현재 시간과 유사한지 확인 (여기서는 ± 1초 차이를 허용)
        LocalDateTime now = LocalDateTime.now();
        assertTrue(savedPost.get().getCreDate().isAfter(now.minusSeconds(1)) &&
                   savedPost.get().getCreDate().isBefore(now.plusSeconds(1)), "작성일자가 올바르지 않습니다.");
    }


//    @Test
//    @DisplayName("게시글 수정")
//    void updatePosts() {
//
//        // 1. 기존 게시글을 저장
//        Member member = Member.builder()
//                .name("홍길동")
//                .email("bWw123@example.com")
//                .build();
//
//        this.memberRepository.save(member);
//
//        PostRequestDto p1 = PostRequestDto.builder()
//                .title("제목1")
//                .content("내용1")
//                .noticeYn(false)
//                .build();
//
//        Posts savedPost = this.postsRepository.save(p1.toEntity(member));
//
//        // 2. 업데이트할 내용 준비
//        Long PostId = savedPost.getId();
//        PostRequestDto update = PostRequestDto.builder()
//                .title("수정 제목")
//                .content("수정 내용")
//                .noticeYn(true)
//                .build();
//
//        // 3. 게시글 조회 후 저장
//        Optional<Posts> post = this.postsRepository.findById(PostId);
//        assertTrue(post.isPresent(), "게시글이 저장되지 않았습니다.");
//
//        Posts existingPost = post.get();
//        existingPost.setTitle(update.getTitle());
//        existingPost.setContent(update.getContent());
//        existingPost.setNoticeYn(update.getNoticeYn());
//
//        // 4. 변경 사항 저장
//        this.postsRepository.save(existingPost);
//
//        // 5. 업데이트된 게시글 검증
//        Optional<Posts> updatedPost = postsRepository.findById(PostId);
//        assertTrue(updatedPost.isPresent(), "업데이트된 게시글이 존재하지 않습니다.");
//        assertEquals("수정 제목", updatedPost.get().getTitle(), "제목이 일치하지 않습니다.");
//        assertEquals("수정 내용", updatedPost.get().getContent(), "내용이 일치하지 않습니다.");
//        assertTrue(updatedPost.get().getNoticeYn(), "공지글 여부가 일치하지 않습니다.");
//    }


//    @Test
//    @DisplayName("게시글 논리적 삭제")
//    void deletePosts() {
//
//        // 1. 기존게시글 저장
//        Member member = Member.builder()
//                .name("홍길동")
//                .email("bWw123@example.com")
//                .build();
//
//        this.memberRepository.save(member);
//
//        PostRequestDto p1 = PostRequestDto.builder()
//                .title("제목1")
//                .content("내용1")
//                .noticeYn(false)
//                .build();
//
//        Posts savedPost = this.postsRepository.save(p1.toEntity(member));
//
//        // 2. 게시글 논리적 삭제 처리
//        Long postId = savedPost.getId();
//
//        Optional<Posts> post = this.postsRepository.findById(postId);
//        assertTrue(post.isPresent(), "게시글이 저장되지 않았습니다.");
//
//        Posts existingPost = post.get();
//        existingPost.setDeleteYn(true);
//        postsRepository.save(existingPost);
//
//        // 3. 삭제 여부 확인
//        Optional<Posts> deletedPost = this.postsRepository.findById(postId);
//        assertTrue(deletedPost.isPresent(), "게시글이 삭제되지 않았습니다.");
//        assertTrue(deletedPost.get().getDeleteYn(), "게시글이 논리적으로 삭제되지 않았습니다.");
//    }


//    @Test
//    @DisplayName("게시글 db 삭제")
//    void deletePosts() {
//
//        // 1. 기존게시글 저장
//        Member member = Member.builder()
//                .name("홍길동")
//                .email("bWw123@example.com")
//                .build();
//
//        this.memberRepository.save(member);
//
//        PostRequestDto p1 = PostRequestDto.builder()
//                .title("제목1")
//                .content("내용1")
//                .noticeYn(false)
//                .build();
//
//        Posts savedPost = this.postsRepository.save(p1.toEntity(member));
//
//        // 2.게시글 db 삭제
//        Long postId = savedPost.getId();
//        this.postsRepository.deleteById(postId);
//
//        // 3. 삭제 여부 확인
//        Optional<Posts> deletedPost = this.postsRepository.findById(postId);
//        assertFalse(deletedPost.isPresent(), "게시글이 db로 삭제되지 않았습니다.");
//    }
}
