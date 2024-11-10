package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.global.auditing.BaseCreateByEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity extends BaseCreateByEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long userId;
    private Long postId;
    private Long parentCommentId;
    private String body;
}
