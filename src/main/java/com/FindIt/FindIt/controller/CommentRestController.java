package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.CommentDto;
import com.FindIt.FindIt.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{postId}/comment")
public class CommentRestController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @ModelAttribute CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(postId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

/*    @GetMapping
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Long postId) {
        log.debug("### postId : {}", postId);
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }*/
}