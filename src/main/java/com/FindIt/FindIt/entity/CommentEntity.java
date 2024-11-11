package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.global.auditing.BaseCreateByEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private  PostEntity post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_comment_id")
    private CommentEntity parentComment;

    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
    private List<CommentEntity> childrenComments = new ArrayList<>();

    private String body;
}
