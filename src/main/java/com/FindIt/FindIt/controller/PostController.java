package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /* 게시글 생성 페이지 이동 */
    @GetMapping("/create")
    public String create() {
        return "post/create";
    }

    @GetMapping
    public String postListPage(Model model) {
        model.addAttribute("items",postService.findAll());
        return "post/postList";
    }

    @GetMapping("/{postId}")
    public String postDetailPage(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        return "post/postDetail";

    }

    @GetMapping("/update"/* /{postId}/ */)
    public String postUpdate(/* @PathVariable int postId, */ Model model) {
        //PostEntity post = postService.findPost(postId);
        //model.addAllAttributes("post", post);
        return "post/update";
    }

}
