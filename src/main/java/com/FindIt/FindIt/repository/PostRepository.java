package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.PostEntity;

import java.util.Optional;

public interface PostRepository {
    Optional<PostEntity> findById(Long postId); // 1건 조회
}
