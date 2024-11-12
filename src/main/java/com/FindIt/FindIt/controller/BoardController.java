package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.service.BoardService;
import com.FindIt.FindIt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;
    private final UserService userService;

    /* userRole을 확인하기 위해서 userDtails 추가 */
    @GetMapping
    public String boardPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("boards", boardService.getBoards());
        model.addAttribute("userRole", userDetails.getRole());
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
    @GetMapping("/update/{boardId}")
    public String showUpdatePage(@PathVariable Long boardId, Model model) {
        BoardDto boardDto = boardService.getBoardById(boardId);
        model.addAttribute("boardDto", boardDto);
        return "board/update";
    }

    // 게시판 수정 api
    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardDto boardDto, @RequestParam("image") MultipartFile imageFile) {
        boardService.updateBoard(boardDto, imageFile);
        return "redirect:/board";
    }

}
