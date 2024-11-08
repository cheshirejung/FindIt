package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.BoardEntity;
import com.FindIt.FindIt.entity.BoardImgEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardImgDto {
    private Long boardImgId;
    private Long boardId;
    private String storePath;

    public BoardImgEntity toEntity(BoardEntity boardEntity) {
        return new BoardImgEntity(this.boardImgId, boardEntity, this.storePath);
    }

}
