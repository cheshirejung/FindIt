package com.FindIt.FindIt.controller;

import ch.qos.logback.core.model.Model;
import com.FindIt.FindIt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* 마이페이지 접근 */
    @GetMapping("/mypage")
    public String myPage(Model model) {
        /* @AuthenticationPrincipal : 스프링 시큐리티인데 현재 로그인한 사용자의 정보를 가져오는데 쓴다고 함 */
        return "mypage";
    }

}
