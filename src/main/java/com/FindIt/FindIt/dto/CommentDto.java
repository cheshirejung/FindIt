package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.CommentEntity;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private Long parentCommentId;
    private String body;
    private boolean isParentComment;

    private List<CommentDto> childrenComments;

    public static CommentDto fromEntity(CommentEntity entity) {
        return CommentDto.builder()
                .commentId(entity.getCommentId())
                .parentCommentId(entity.getParentComment().getCommentId())
                .body(entity.getBody())
                .isParentComment(entity.getParentComment() == null)
                .childrenComments(new ArrayList<>())
                .build();
    }

    public CommentEntity toEntity(UserEntity user, PostEntity post,CommentEntity parentComment) {
        return CommentEntity.builder()
                .user(user)
                .post(post)
                .body(this.body)
                .parentComment(parentComment)
                .build();
    }

}
