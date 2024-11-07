package com.FindIt.FindIt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardImgDto {
    private Long boardImgId;
    private Long boardId;
    private String storePath;
}
