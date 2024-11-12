package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.PostReqDto;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String create(@RequestParam(value = "boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostReqDto postReqDto, Model model) {
        try {
            postService.savePost(postReqDto);
            return "redirect:/post?boardId="+postReqDto.getBoardId();
        } catch (Exception e) {
            model.addAttribute("error", "게시글 생성 중 오류가 발생했습니다.");
            return "error";
        }
    }

    @GetMapping
    public String postListPage(@RequestParam("boardId") Long boardId, Model model) {
        List<PostEntity> posts = postService.findPostsByBoardId(boardId);
        model.addAttribute("posts",posts);
        model.addAttribute("boardId", boardId);
        return "post/postList";
    }

    @GetMapping("/{postId}")
    public String postDetailPage(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        return "post/postDetail";

    }

    @GetMapping("/update/{postId}")
    public String postUpdatePage(@PathVariable Long postId, Model model) {
        PostEntity post = postService.findById(postId);
        model.addAttribute("post", post);
        return "post/update";
    }

    @PatchMapping("/update/{postId}")
    public String postUpdate(@PathVariable Long postId, @ModelAttribute PostReqDto postReqDto) {
        postService.updatePost(postId, postReqDto);
        return "redirect:/post/" + postId;
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, @RequestParam Long boardId) {
        try {
            postService.deletePost(postId);
            return "redirect:/post?boardId=" + boardId;
        } catch (Exception e) {
            // 에러 처리
            return "error";

        }
    }
}

