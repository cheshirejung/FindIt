package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    Page<PostEntity> findByBoardId(Long boardId, Pageable pageable);
    Page<PostEntity> findPostsByBoardIdAndCategory(Long boardId, String Category, Pageable pageable);
}
