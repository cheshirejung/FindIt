package com.FindIt.FindIt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String title;
    private Long userId;
    private Long boardImgId;

}
