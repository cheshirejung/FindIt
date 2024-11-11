package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.entity.BoardEntity;
import com.FindIt.FindIt.entity.BoardImgEntity;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.BoardImgRepository;
import com.FindIt.FindIt.repository.BoardRepository;
import com.FindIt.FindIt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class BoardService {
    private final BoardRepository boardRepository; // 게시판 저장
    private final BoardImgRepository boardImgRepository; // 게시판 이미지 저장
    private final ImageService imageService; // 게시판 이미지 저장
    private final UserRepository userRepository; //로그인 되어있는 유정 정보 조회

    @Autowired
    public BoardService(BoardRepository boardRepository, BoardImgRepository boardImgRepository, ImageService imageService, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.boardImgRepository = boardImgRepository;
        this.imageService = imageService;
        this.userRepository = userRepository;
    }



    public Page<BoardDto> getBoards(Pageable pageable) {
        // Page<BoardEntity>를 Pageable로 가져옵니다.
        Page<BoardEntity> boardEntityPage = boardRepository.findAll(pageable);

        // BoardEntity를 BoardDto로 변환합니다.
        List<BoardDto> boardDtos = boardEntityPage.getContent().stream()
                .map(BoardEntity::toDto)
                .collect(Collectors.toList());

        // 변환된 BoardDto를 포함하는 Page 객체를 반환합니다.
        return new PageImpl<>(boardDtos, pageable, boardEntityPage.getTotalElements());
    }

    @Transactional
    public void createBoard(BoardReqDto boardReqDto) {
        UserEntity user = userRepository.findLoginUserByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());



        BoardEntity boardEntity = BoardEntity.builder()
                .title(boardReqDto.getTitle())
                .user(user)
                .build();

        boardRepository.save(boardEntity);

        String imagePath = imageService.uploadImage("board", boardReqDto.getBoardImage());


        BoardImgEntity boardImgEntity = new BoardImgEntity();
        boardImgEntity.setStorePath(imagePath);
        boardImgEntity.setBoardEntity(boardEntity);

        boardImgRepository.save(boardImgEntity);

        boardEntity.setBoardImg(boardImgEntity);
        boardRepository.save(boardEntity);
    }

}
