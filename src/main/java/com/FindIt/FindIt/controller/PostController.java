package com.FindIt.FindIt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

    /* 게시판 생성 페이지 이동 */
    @GetMapping("/create")
    public String create() {
        return "create";
    }
}
