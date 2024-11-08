package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.dto.BoardDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardEntity extends BaseEntity{
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


