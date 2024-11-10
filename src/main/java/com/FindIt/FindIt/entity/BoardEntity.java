package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.global.auditing.BaseCreateByEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class BoardEntity extends BaseCreateByEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long boardId;
    @Column(name = "title")
    private String title;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "board_img_id")
    private Long boardImgId;

    public BoardDto toDto() {
        return new BoardDto(this.boardId, this.title, this.userId, this.boardImgId);
    }
}


