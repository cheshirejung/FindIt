package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원 가입 페이지
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "user/signup"; // signup 뷰 반환
    }

    // 회원 가입 api
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute UserEntity userEntity, Model model) {
        try { // 로그인 id 중복 검사
            userService.registerUser(userEntity);
            return "redirect:/user/login?success=true";
        } catch (IllegalArgumentException e) {
            // 오류 메시지를 모델에 추가하고 회원가입 페이지로 리턴
            model.addAttribute("errorMessage", e.getMessage());
            return "user/signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

}
