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

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;
    private final ImageService imageService;

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
            String imagePath = imageService.uploadImage("post", postReqDto.getPostImage());
            PostImgEntity postImgEntity = new PostImgEntity();
            postImgEntity.setStorePath(imagePath);
            postImgEntity.setPostEntity(postEntity);

            postImgRepository.save(postImgEntity);
        }
    }

}
