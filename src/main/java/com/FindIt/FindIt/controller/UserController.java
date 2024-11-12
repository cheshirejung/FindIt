package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.UserSignupDto;
import com.FindIt.FindIt.dto.UserWithdrawDto;
import com.FindIt.FindIt.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        /* @AuthenticationPrincipal : 스프링 시큐리티인데 현재 로그인한 사용자의 정보를 가져오는데 쓴다고 함 */
        return "user/mypage";
    }

    // 회원 탈퇴 api
    @DeleteMapping
    public String deleteUser(@ModelAttribute UserWithdrawDto userWithdrawDto, HttpSession session, Model model) {
        String sessionLoginId = (String) session.getAttribute("loginId");

        if (sessionLoginId == null) {
            return "redirect:/user/login";
        }
        if (!sessionLoginId.equals(userWithdrawDto.getLoginId())) {
            return "redirect:/user/mypage";
        }

        boolean isDeleted = userService.deleteUser(userWithdrawDto);
        if (isDeleted) {
            session.invalidate();
            return "redirect:/user/login";
        } else {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "user/mypage";
        }
    }

    /*로그인 페이지 접근*/
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

}
