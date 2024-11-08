package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    @GetMapping
    public String boardPage(){
        return "board";
    }

    // 게시판 생성 페이지
    @GetMapping("/create")
    public String boardCreatePage() {
        return "board/create";
    }

    // 게시판 생성 api
    @PostMapping("/create")
    public ResponseEntity<BoardDto> createBoard(@ModelAttribute BoardReqDto boardReqDto) {
        try {
            BoardDto savedBoardDto = boardService.createBoard(boardReqDto.getUserId(), boardReqDto.getTitle(), boardReqDto.getBoardImage());
            return new ResponseEntity<>(savedBoardDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시판 수정 페이지
    @GetMapping("/update")
    public String boardUpdatePage() {
        return "board/update";
    }

}
