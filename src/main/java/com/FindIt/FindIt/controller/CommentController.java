package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.CommentDto;
import com.FindIt.FindIt.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.noContent().build(); // 204 No Content 응답
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러가 발생하면 500 응답
        }
    }


}