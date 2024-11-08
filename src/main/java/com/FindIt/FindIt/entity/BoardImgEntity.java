package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.dto.BoardImgDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_img_id")
    private Long boardImgId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    @Column(name = "store_path")
    private String storePath;

    public BoardImgDto toDto() {
        return new BoardImgDto(
                this.boardImgId,
                this.boardEntity != null ? this.boardEntity.getBoardId() : null,
                this.storePath
        );
    }
}
