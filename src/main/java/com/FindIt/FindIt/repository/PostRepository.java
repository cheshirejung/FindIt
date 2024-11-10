package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findByBoardId(Long boardId);
}
