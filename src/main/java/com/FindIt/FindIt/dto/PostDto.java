package com.FindIt.FindIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long postId;
    private String title;
    private Long boardId;
    private String body;
    private String userId;
}
