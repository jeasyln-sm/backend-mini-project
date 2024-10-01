package com.smsm.controller;

import com.smsm.dto.PostResponseDto;
import com.smsm.entity.Posts;
import com.smsm.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostsController {

    private final PostsService postService;

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
    @GetMapping("/create")
    public String register(Model model) {
        model.addAttribute("postDto", new PostResponseDto());
        return "posts/postsForm";
    }
}
