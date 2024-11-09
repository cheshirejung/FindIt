package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.global.auditing.BaseCreateByEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class PostEntity extends BaseCreateByEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "title")
    private String title;
    @Column(name = "board_id")
    private Long boardId;
    @Column(name = "body")
    private String body;
    @Column(name = "user_id")
    private String userId;

}
