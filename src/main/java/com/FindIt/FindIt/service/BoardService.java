package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.BoardDto;
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
    private final UserRepository userRepository; //로그인 되어있는 유정 정보 조회

    @Autowired
    public BoardService(BoardRepository boardRepository, BoardImgRepository boardImgRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.boardImgRepository = boardImgRepository;
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
    public BoardDto createBoard(Long userId, String title, MultipartFile boardImage) {
        //log.debug("######### createBoard 111");
        String imagePath = uploadImage("board", boardImage); // 이미지 저장 후 경로 반환

        UserEntity user = userRepository.findLoginUserByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());

        // BoardEntity 생성 및 저장
        BoardEntity boardEntity = new BoardEntity(null, title, user, null);
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);

        // BoardImgEntity 생성 및 저장
        BoardImgEntity boardImgEntity = new BoardImgEntity();
        boardImgEntity.setStorePath(imagePath);
        boardImgEntity.setBoardEntity(savedBoardEntity);
        BoardImgEntity savedBoardImgEntity = boardImgRepository.save(boardImgEntity);

        // BoardEntity에 boardImgId 저장
        savedBoardEntity.setBoardImgId(savedBoardImgEntity.getBoardImgId());
        boardRepository.save(savedBoardEntity);

        // Entity -> Dto 변환
        BoardDto boardDto = savedBoardEntity.toDto();
        boardDto.setBoardImgId(savedBoardImgEntity.getBoardImgId());

        return boardDto;
    }

    // 이미지 업로드 메서드
    private String uploadImage(String subPath, MultipartFile image) {
        String imageDirectoryPath = "C:/webserver_storage/"+ subPath +"/";
        log.debug("######### imageDirectoryPath : " + imageDirectoryPath);
        File imageDirectory = new File(imageDirectoryPath);

        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }

        String extension = "";
        if (image != null) {
            extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
            if (extension != null) {
                log.debug("######### extension : " + extension);
            } else {
                throw new IllegalArgumentException("No file extension");
            }
        }

        String savePath = imageDirectoryPath;
        log.debug("######### savePath : " + savePath);
        String saveFilename = UUID.randomUUID() +"."+ extension;
        log.debug("######### saveFilename : " + saveFilename);
        String fullPath = savePath + saveFilename;
        log.debug("######### fullPath : " + fullPath);
        File file = new File(fullPath);

        try {
            image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image upload failed: " + e.getMessage());
        }
        String dbPath = "/upload/"+ subPath +"/" + saveFilename;
        log.debug("######### dbPath : " + dbPath);

        return dbPath;
    }

}
