package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.entity.BoardEntity;
import com.FindIt.FindIt.entity.BoardImgEntity;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.PostImgEntity;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.BoardImgRepository;
import com.FindIt.FindIt.repository.BoardRepository;
import com.FindIt.FindIt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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



    public List<BoardDto> getBoards() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntities) {
            boardDtos.add(boardEntity.toDto());
        }
        return boardDtos;
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

        boardEntity.setBoardImgId(boardImgEntity.getBoardImgId());
        boardRepository.save(boardEntity);
    }

}
