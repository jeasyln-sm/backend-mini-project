package com.smsm.controller;

import com.smsm.dto.PostRequestDto;
import com.smsm.dto.PostResponseDto;
import com.smsm.entity.Member;
import com.smsm.service.MemberService;
import com.smsm.service.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostsController {

    private final PostsService postService;
    private final MemberService memberService;

    // 게시글 목록
    @GetMapping("/list")
    public String postsList(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<PostResponseDto> postsPage = postService.getPosts(pageable);
        model.addAttribute("posts", postsPage.getContent());
        model.addAttribute("paging", postsPage);
        return "posts/postsList";
    }


    // 게시글 등록 -> form으로 이동
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("postDto", new PostResponseDto());
        return "posts/postsForm";
    }

    // 게시글 등록
//    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createPost(@Valid PostRequestDto postDto, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "posts/postsForm";
        }

        // 현재 로그인 한 사용자 정보
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        postService.savePost(postDto, member);

        return "redirect:/posts/list";
    }
}
