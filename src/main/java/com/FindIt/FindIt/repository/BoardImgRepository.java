package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.BoardEntity;
import com.FindIt.FindIt.entity.BoardImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImgRepository extends JpaRepository<BoardImgEntity,Long> {
    BoardImgEntity findByBoardEntity(BoardEntity boardEntity);
}
