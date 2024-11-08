package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.entity.BoardEntity;
import com.FindIt.FindIt.entity.BoardImgEntity;
import com.FindIt.FindIt.repository.BoardImgRepository;
import com.FindIt.FindIt.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class BoardService {
    private final BoardRepository boardRepository; // 게시판 저장
    private final BoardImgRepository boardImgRepository; // 게시판 이미지 저장

    @Autowired
    public BoardService(BoardRepository boardRepository, BoardImgRepository boardImgRepository) {
        this.boardRepository = boardRepository;
        this.boardImgRepository = boardImgRepository;
    }

    @Transactional
    public BoardDto createBoard(Long userId, String title, MultipartFile boardImage) {
        String imagePath = uploadImage(boardImage); // 이미지 저장 후 경로 반환

        // BoardEntity 생성 및 저장
        BoardEntity boardEntity = new BoardEntity(null, title, userId, null);
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);

        // BoardImgEntity 생성 및 저장
        BoardImgEntity boardImgEntity = new BoardImgEntity();
        boardImgEntity.setStorePath(imagePath);
        boardImgEntity.setBoardEntity(savedBoardEntity);
        BoardImgEntity savedBoardImgEntity = boardImgRepository.save(boardImgEntity);

        // Entity -> Dto 변환
        BoardDto boardDto = savedBoardEntity.toDto();
        boardDto.setBoardImgId(savedBoardImgEntity.getBoardImgId());

        return boardDto;
    }

    // 이미지 업로드 메서드
    private String uploadImage(MultipartFile image) {
        String imageDirectoryPath = "src/main/resources/static/images";
        File imageDirectory = new File(imageDirectoryPath);

        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }

        String imagePath = imageDirectoryPath + "/" + image.getOriginalFilename();
        File file = new File(imagePath);

        try {
            image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image upload failed: " + e.getMessage());
        }

        return "/images/" + image.getOriginalFilename();
    }

}
