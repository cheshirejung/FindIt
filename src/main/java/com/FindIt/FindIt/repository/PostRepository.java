package com.FindIt.FindIt.repository;

import com.FindIt.FindIt.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    /**
     * @param boardId
     * @param pageable
     * @return
     * boardId로만 검색
     */
    Page<PostEntity> findByBoardId(Long boardId, Pageable pageable);

    /**
     * @param boardId
     * @param Category
     * @param pageable
     * @return
     * boardId,Category 검색
     */
    Page<PostEntity> findPostsByBoardIdAndCategory(Long boardId, String Category, Pageable pageable);

    /**
     * @param boardId
     * @param keyword
     * @param pageable
     * @return
     * boardId,keyword 검색
     */
    Page<PostEntity> findPostsByBoardIdAndTitleContaining(Long boardId, String keyword, Pageable pageable);


    /**
     * @param boardId
     * @param category
     * @param keyword
     * @param pageable
     * @return
     * boardId,category,keyword 검색
     */

    Page<PostEntity> findByBoardIdAndCategoryAndTitleContaining(Long boardId, String category, String keyword, Pageable pageable);

}
