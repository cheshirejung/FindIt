package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String title;
    //private String loginId;
    private Long boardImgId;


}
