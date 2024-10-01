package com.smsm.dto;

import com.smsm.entity.Member;
import com.smsm.entity.Posts;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {

    private Long id;

    @NotEmpty(message = "제목은 필수항목입니다.")
	@Size(max = 200)
    private String title;        // 제목

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;      // 내용

    private Boolean noticeYn;    // 공지글 여부


    // toEntity()
    public Posts toEntity(Member member) {
        return Posts.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .noticeYn(this.noticeYn != null ? this.noticeYn : false)
                .member(member)
                .build();
    }
}
