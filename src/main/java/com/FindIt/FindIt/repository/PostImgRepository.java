package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.PostImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostImgRepository extends JpaRepository<PostImgEntity, Long> {
    Optional<PostImgEntity> findByPost(PostEntity postEntity);
}
