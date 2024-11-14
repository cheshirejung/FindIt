package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.CommentEntity;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}