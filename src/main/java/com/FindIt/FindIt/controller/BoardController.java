package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.BoardDto;
import com.FindIt.FindIt.dto.BoardReqDto;
import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.service.BoardService;
import com.FindIt.FindIt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String boardPage(@PageableDefault(page = 1) Pageable pageable ,Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        int page = pageable.getPageNumber() - 1;

        pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "boardId");

        Page<BoardDto> boardDtoPage = boardService.getBoards(pageable);
        model.addAttribute("boards", boardDtoPage);
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
    public String createBoard(@ModelAttribute BoardReqDto boardReqDto, Model model,@AuthenticationPrincipal CustomUserDetails userDetails ) {
        try {
            boardService.createBoard(boardReqDto,userDetails);
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


    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardDto boardDto,@RequestParam("image") MultipartFile imageFile) {
        boardService.updateBoard(boardDto, imageFile);
        return "redirect:/board";
    }
}
