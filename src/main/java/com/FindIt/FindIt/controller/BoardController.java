package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    @GetMapping
    public String boardPage(){
        return "/board/boardList";
    }

    // 게시판 생성 페이지
    @GetMapping("/create")
    public String boardCreatePage() {
        return "/board/create";
    }

    // 게시판 생성 api
    @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardReqDto boardReqDto) {
        log.debug("@@@@@@@@@@@@ createBoard");
        boardService.createBoard(boardReqDto.getUserId(), boardReqDto.getTitle(), boardReqDto.getBoardImage());
        return "redirect:/board";
    }

    // 게시판 수정 페이지
    @GetMapping("/update")
    public String boardUpdatePage() {
        return "board/update";
    }

}
