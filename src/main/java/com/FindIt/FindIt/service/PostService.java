package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.dto.PostDto;
import com.FindIt.FindIt.dto.PostReqDto;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.PostImgEntity;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.PostImgRepository;
import com.FindIt.FindIt.repository.PostRepository;
import com.FindIt.FindIt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;

    //public List<PostEntity> findAll() {return postRepository.findAll();}

    /*boardId로 필터링하여 게시글 검색*/
    /*반환 타입 추후 DTO로 수정할 것*/
    public Page<PostDto> findPostsByBoardId(Long boardId, Pageable pageable){
        Page<PostEntity> postEntitiePage = postRepository.findByBoardId(boardId,pageable);

        List<PostDto> postDtos = postEntitiePage.getContent().stream()
                .map(PostEntity::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(postDtos, pageable, postEntitiePage.getTotalElements());
    }

    public Page<PostDto> findPostsByBoardIdAndCategory(Long boardId, String category, Pageable pageable){
        Page<PostEntity> postEntitiePage = postRepository.findPostsByBoardIdAndCategory(boardId,category,pageable);

        List<PostDto> postDtos = postEntitiePage.getContent().stream()
                .map(PostEntity::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(postDtos, pageable, postEntitiePage.getTotalElements());
    }


    public PostEntity findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));
    }

    @Transactional
    public void savePost(PostReqDto postReqDto,Long boardId, CustomUserDetails userDetails){
        UserEntity user = userDetails.getUser();

        PostEntity postEntity = PostEntity.builder()
                .title(postReqDto.getTitle())
                .body(postReqDto.getBody())
                .boardId(boardId)
                .category(postReqDto.getCategory())
                .user(user)
                .build();

        postRepository.save(postEntity);

        // 이미지가 있을 경우, 이미지 저장
        if (postReqDto.getPostImage() != null && !postReqDto.getPostImage().isEmpty()) {
            String imagePath = imageService.uploadImage("post", postReqDto.getPostImage());
            PostImgEntity postImgEntity = new PostImgEntity();
            postImgEntity.setStorePath(imagePath);
            postImgEntity.setPost(postEntity);

            postImgRepository.save(postImgEntity);
        }
    }

    @Transactional
    public void updatePost(Long postId, PostReqDto postReqDto) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.setTitle(postReqDto.getTitle());
        post.setBody(postReqDto.getBody());

        Optional.ofNullable(postReqDto.getPostImage())
                .filter(newImageFile -> !newImageFile.isEmpty())
                .ifPresent(newImageFile -> {
                    String newImagePath = imageService.uploadImage("post", newImageFile);

                    postImgRepository.findByPost(post)
                            .map(existingImage -> {
                                existingImage.setStorePath(newImagePath);
                                return postImgRepository.save(existingImage);
                            })
                            .orElseGet(() -> {
                                PostImgEntity newPostImgEntity = new PostImgEntity();
                                newPostImgEntity.setPost(post);
                                newPostImgEntity.setStorePath(newImagePath);
                                return postImgRepository.save(newPostImgEntity);
                            });
                });

        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        postImgRepository.findByPost(post).ifPresent(postImg -> {

            imageService.deleteImage(postImg.getStorePath());

            postImgRepository.delete(postImg);
        });

        postRepository.delete(post);

    }
}
