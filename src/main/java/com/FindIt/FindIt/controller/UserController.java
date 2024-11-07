package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.LoginReqDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {


    // 회원 가입 페이지
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // signup 뷰 반환
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }

}
