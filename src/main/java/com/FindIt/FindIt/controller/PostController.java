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

    /* 게시판 생성 페이지 이동 */
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping
    public String postListPage(Model model) {
        model.addAttribute("items",postService.findAll());
        return "postList";
    }

    @GetMapping("/{id}")
    public String postDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "postDetail";

    }

    @GetMapping("/update"/* /{postId}/ */)
    public String postUpdate(/* @PathVariable int postId, */ Model model) {
        //PostEntity post = postService.findPost(postId);
        //model.addAllAttributes("post", post);
        return "update";
    }

}
