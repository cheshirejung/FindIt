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
    @PatchMapping("{commentId}")
    public String updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @ModelAttribute CommentDto commentDto) {

        return "redirect:/post/" + postId;
    }
}