package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    @GetMapping
    public String boardPage(Model model){
        model.addAttribute("boards", boardService.getBoards());
        return "/board/boardList";
    }

    // 게시판 생성 페이지
    @GetMapping("/create")
    public String boardCreatePage() {
        return "/board/create";
    }

    // 게시판 생성 api
    @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardReqDto boardReqDto, Model model) {
        try {
            boardService.createBoard(boardReqDto);
            return "redirect:/board";
        } catch (Exception e) {
            model.addAttribute("error", "게시판 생성 중 오류가 발생했습니다.");
            return "error";
        }
    }

    // 게시판 수정 페이지
    @GetMapping("/update")
    public String boardUpdatePage() {
        return "board/update";
    }






    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board";
    }

}
