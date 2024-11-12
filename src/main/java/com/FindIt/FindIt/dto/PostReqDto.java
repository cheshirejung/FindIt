package com.FindIt.FindIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostReqDto {
    private String title;
    private String body;
    private MultipartFile postImage;
    private String category;
    private Long boardId;
    private String userId;
}
