package com.FindIt.FindIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long postId;
    private String title;
    private Long boardId;
    private String body;
    private String postImgUrl;
    private String createdAt;
    private String createdBy;
    private List<CommentDto> comments;
    private String category;
}
