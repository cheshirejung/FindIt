package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.PostDto;
import com.FindIt.FindIt.dto.PostReqDto;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String postListPage(@PageableDefault(page = 1) Pageable pageable,@RequestParam("boardId") Long boardId, Model model) {
        int page = pageable.getPageNumber() - 1;

        pageable = PageRequest.of(page, 5,Sort.Direction.DESC, "postId");
        Page<PostDto> postDtos = postService.findPostsByBoardId(boardId, pageable);
        model.addAttribute("posts",postDtos);
        model.addAttribute("boardId", boardId);
        model.addAttribute("pageNo", page);
        return "post/postList";
    }

    @GetMapping("/{postId}")
    public String postDetailPage( @RequestParam("pageNo") int pageNo, @PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        model.addAttribute("pageNo", pageNo);
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

