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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


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

        boardEntity.setBoardImg(boardImgEntity);
        boardRepository.save(boardEntity);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);

    }
    public BoardDto getBoardById(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다."));

        BoardImgEntity boardImgEntity = boardImgRepository.findByBoardEntity(boardEntity);
        String imageUrl = boardImgEntity != null ? boardImgEntity.getStorePath() : null;

        return new BoardDto(boardEntity.getBoardId(), boardEntity.getTitle(), imageUrl);
    }

    public void updateBoard(BoardDto boardDto, MultipartFile imageFile) {
        BoardEntity boardEntity = boardRepository.findById(boardDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다."));

        boardEntity.setTitle(boardDto.getTitle());

        if (!imageFile.isEmpty()) {
            String newImagePath = imageService.uploadImage("board_images", imageFile);
            BoardImgEntity boardImgEntity = boardImgRepository.findByBoardEntity(boardEntity);

            if (boardImgEntity != null) {
                boardImgEntity.setStorePath(newImagePath);
            } else {
                boardImgEntity = new BoardImgEntity();
                boardImgEntity.setBoardEntity(boardEntity);
                boardImgEntity.setStorePath(newImagePath);
                boardImgRepository.save(boardImgEntity);
            }
        }
        boardRepository.save(boardEntity);
    }

}
