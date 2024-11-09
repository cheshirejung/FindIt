package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.PostReqDto;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.PostImgEntity;
import com.FindIt.FindIt.repository.PostImgRepository;
import com.FindIt.FindIt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;

    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }
    public Optional<PostEntity> findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void savePost(PostReqDto postReqDto){
        PostEntity postEntity = PostEntity.builder()
                .title(postReqDto.getTitle())
                .body(postReqDto.getBody())
                .boardId(postReqDto.getBoardId())
                .userId(postReqDto.getUserId())
                .build();

        postRepository.save(postEntity);

        // 이미지가 있을 경우, 이미지 저장
        if (postReqDto.getPostImage() != null && !postReqDto.getPostImage().isEmpty()) {
            String imagePath = uploadImage("post", postReqDto.getPostImage());
            PostImgEntity postImgEntity = new PostImgEntity();
            postImgEntity.setStorePath(imagePath);
            postImgEntity.setPostEntity(postEntity);

            postImgRepository.save(postImgEntity);
        }
    }

    // 이미지 업로드 메서드
    private String uploadImage(String subPath, MultipartFile image) {
        String imageDirectoryPath = "C:/webserver_storage/"+ subPath +"/";
        File imageDirectory = new File(imageDirectoryPath);

        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }

        String extension = "";
        if (image != null) {
            extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
            if (extension != null) {
            } else {
                throw new IllegalArgumentException("No file extension");
            }
        }

        String savePath = imageDirectoryPath;
        String saveFilename = UUID.randomUUID() +"."+ extension;
        String fullPath = savePath + saveFilename;
        File file = new File(fullPath);

        try {
            image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image upload failed: " + e.getMessage());
        }
        String dbPath = "/upload/"+ subPath +"/" + saveFilename;

        return dbPath;
    }


}
