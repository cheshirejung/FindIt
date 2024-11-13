package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.dto.UserDto;
import com.FindIt.FindIt.dto.UserSignupDto;
import com.FindIt.FindIt.dto.UserWithdrawDto;
import com.FindIt.FindIt.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        model.addAttribute("userSignupDto", new UserSignupDto());
        return "user/signup"; // signup 뷰 반환
    }

    // 회원 가입 api
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute UserSignupDto userSignupDto, Model model) {
        try { // 로그인 id 중복 검사
            userService.registerUser(userSignupDto);
            return "redirect:/user/login?success=true";
        } catch (IllegalArgumentException e) {
            // 오류 메시지를 모델에 추가하고 회원가입 페이지로 리턴
            model.addAttribute("errorMessage", e.getMessage());
            return "user/signup";
        }
    }

    /* 마이페이지 접근 */
    @GetMapping("/mypage")
    public String myPage(Model model) {
        // 현재 로그인한 사용자의 정보를 가져옴
        UserDto userInfo = userService.getUser();
        model.addAttribute("user", userInfo);
        return "user/mypage";

    }

    // 회원 탈퇴 api
    @DeleteMapping
    public String deleteUser(@ModelAttribute UserWithdrawDto userWithdrawDto, HttpSession session, Model model,
                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        //String sessionLoginId = (String) session.getAttribute("loginId");
        //User sessionUser = (User) session.getAttribute("user");
        log.debug("########## login id : " + userWithdrawDto.getLoginId());

        UserDto userDto = userService.deleteUser(userWithdrawDto, userDetails);
        log.debug("########## service result: {}", userDto.getLoginId());
        if (userDto == null) {
            session.invalidate();
            return "redirect:/user/login";
        } else {
            model.addAttribute("user", userDto);
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            log.debug("########## modelAttribute");
            return "user/mypage";
        }
    }

    /*로그인 페이지 접근*/
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

}
