package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.global.auditing.BaseCreateByEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class BoardEntity extends BaseCreateByEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "title")
    private String title;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = LAZY)
    private BoardImgEntity boardImg;

    public BoardDto toDto() {
        // 이미지 경로가 존재하는 경우에만 가져옴
        String imageUrl = boardImg != null ? boardImg.getStorePath() : null;

        return new BoardDto(this.boardId, this.title, imageUrl);
    }
}


