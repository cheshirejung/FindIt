package com.FindIt.FindIt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardReqDto {
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;
    @NotNull(message = "게시판 썸네일 사진을 업로드 해주셔야 합니다")
    private MultipartFile boardImage;
}
