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
    private Long boardId;
    @NotNull
    private String title;
    @NotNull
    private Long userId;
    @NotNull
    private Long boardImgId;

    public BoardDto toDto() {
        return new BoardDto(this.boardId, this.title, this.userId, this.boardImgId);
    }
}


