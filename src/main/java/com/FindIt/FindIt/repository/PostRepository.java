package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
