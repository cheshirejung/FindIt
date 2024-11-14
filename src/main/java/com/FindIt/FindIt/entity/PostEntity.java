package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.dto.CommentDto;
import com.FindIt.FindIt.dto.PostDto;
import com.FindIt.FindIt.global.auditing.BaseCreateByEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

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

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = LAZY)
    private PostImgEntity postImg;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

    public String getPostImgPath() {
        return postImg != null ? postImg.getStorePath() : null;
    }

    public PostDto toDto() {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (CommentEntity comment : comments) {
            commentDtos.add(CommentDto.fromEntity(comment));
        }
         return new PostDto(this.postId,this.title,this.boardId,this.body,this.postImg.getStorePath(),this.getCreatedAt().toString(),this.getCreatedBy(),commentDtos, this.category);
    }
}
