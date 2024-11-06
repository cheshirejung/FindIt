package com.FindIt.FindIt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // 회원 가입 페이지
    @GetMapping("/signup")
    public String showSignupPage() {

        return "signup"; // signup 뷰 반환
    }

}
