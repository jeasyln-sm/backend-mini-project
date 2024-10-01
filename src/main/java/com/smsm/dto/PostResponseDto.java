package com.smsm.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;                         // 게시글 ID

    @NotEmpty(message = "제목은 필수항목입니다.")
	@Size(max = 200)
    private String title;                    // 제목

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;                  // 내용

    private String member;                   // 작성자 이름 (Member 엔티티의 정보)

    @Positive(message = "조회 수는 0 이상의 값이어야 합니다.")
    private int viewCnt;                     // 조회 수

    private Boolean noticeYn;                // 공지글 여부

    private Boolean deleteYn;                // 삭제 여부

    @NotNull(message = "작성일시는 필수항목입니다.")
    private LocalDateTime creDate;           // 작성일시

    @NotNull(message = "최종 수정일시는 필수항목입니다.")
    private LocalDateTime modDate;           // 최종 수정일시
}
