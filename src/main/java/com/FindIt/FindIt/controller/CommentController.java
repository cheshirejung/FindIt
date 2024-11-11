package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.CommentDto;
import com.FindIt.FindIt.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public /*ResponseEntity<CommentDto>*/ String createComment(@PathVariable("postId") Long postId, @ModelAttribute CommentDto commentDto) {
        commentService.createComment(postId, commentDto);
        /*return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);*/
        return "redirect:/post/" + postId;
    }
    /*@PatchMapping*/ //html 폼 태그 형식 상 post/get 만 지원
                     // hidden을 사용하여 method 변경할려했으나 적용이 안되어 추후 다른 방도로 변경
                     // 일단은 post로 적용
    @PostMapping("{commentId}")
    public String updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @ModelAttribute CommentDto commentDto) {
        commentService.updateComment(commentId, commentDto);
        return "redirect:/post/" + postId;
    }
}