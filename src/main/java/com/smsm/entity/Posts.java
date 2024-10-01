package com.smsm.entity;

import com.smsm.dto.PostRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;           // 제목

    @Column(columnDefinition = "TEXT", nullable = false, length = 3000)
    private String content;         // 내용

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;          // 작성자

    @Column(nullable = false)
    @Builder.Default
    private int viewCnt = 0;  // 조회 수, 기본값 0

    @CreationTimestamp
    private LocalDateTime creDate;  // 작성일

    private LocalDateTime modDate;  // 변경일

    @Column(nullable = false)
    @Builder.Default
    private Boolean noticeYn = false;  // 공지글 여부, 기본값 false

    @Column(nullable = false)
    @Builder.Default
    private Boolean deleteYn = false;  // 삭제 여부, 기본값 false


    @PreUpdate
    public void setLastModifiedDate() {
        this.creDate = LocalDateTime.now();
        this.modDate = LocalDateTime.now();
    }


    // 게시글 등록
    public static Posts createPost(PostRequestDto postRequestDto, Member member) {
        Posts posts = new Posts();
        posts.setTitle(postRequestDto.getTitle());
        posts.setContent(postRequestDto.getContent());
        posts.setMember(member);
        posts.setNoticeYn(postRequestDto.getNoticeYn() != null && postRequestDto.getNoticeYn());
        posts.setViewCnt(0);
        posts.setCreDate(LocalDateTime.now());
        posts.setModDate(LocalDateTime.now());
        posts.setDeleteYn(false);
        return posts;
    }
}
