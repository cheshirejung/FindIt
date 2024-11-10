package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private Long userId;
    private Long postId;
    private Long parentCommentId;
    private String body;
    private String createdAt;
    private List<CommentDto> replies;


    public static CommentDto fromEntity(CommentEntity entity) {
        return CommentDto.builder()
                .commentId(entity.getCommentId())
                .userId(entity.getUserId())
                .postId(entity.getPostId())
                .parentCommentId(entity.getParentCommentId())
                .body(entity.getBody())
                .createdAt(entity.getCreatedAt())
                .replies(new ArrayList<>())
                .build();
    }

    public CommentEntity toEntity() {
        return CommentEntity.builder()
                .commentId(this.commentId)
                .userId(this.userId)
                .postId(this.postId)
                .parentCommentId(this.parentCommentId)
                .body(this.body)
                .build();
    }
}
