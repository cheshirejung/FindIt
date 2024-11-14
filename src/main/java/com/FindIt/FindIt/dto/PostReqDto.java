package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.global.vlidation.ValidFile;
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
public class PostReqDto {

    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String body;

    @ValidFile(message = "물건 사진은 업로드 해주셔야 합니다")
    private MultipartFile postImage;

    @NotBlank(message = "카테고리를 선택해주셔야 합니다")
    private String category;

}
