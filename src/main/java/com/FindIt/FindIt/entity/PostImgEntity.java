package com.FindIt.FindIt.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class PostImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_img_id")
    private Long postImgId;


    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "store_path")
    private String storePath;
}
