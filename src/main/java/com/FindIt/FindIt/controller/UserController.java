package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.dto.UserDto;
import com.FindIt.FindIt.dto.UserSignupDto;
import com.FindIt.FindIt.dto.UserUpdateDto;
import com.FindIt.FindIt.dto.UserWithdrawDto;
import com.FindIt.FindIt.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registerUser(@Valid @ModelAttribute UserSignupDto userSignupDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "user/signup";
        }
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
        model.addAttribute("userUpdateDto", new UserUpdateDto());
        return "user/mypage";

    }


    /* 회원 정보 수정 */
    @PatchMapping
    public String updateUser(@ModelAttribute UserUpdateDto userUpdateDto, Model model) {
        try {
            userService.updateUser(userUpdateDto);
            model.addAttribute("message", "프로필이 성공적으로 업데이트되었습니다.");
            return "redirect:/user/mypage";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/user/mypage";
        }
    }

    // 회원 탈퇴 api
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@ModelAttribute UserWithdrawDto userWithdrawDto, HttpSession session, Model model) {
        log.debug("########## login id : " + userWithdrawDto.getLoginId());
        boolean isDelete = userService.deleteUser(userWithdrawDto);
        //log.debug("########## service result: {}", userDto.getLoginId());

        if (isDelete) {
            session.invalidate();
            log.debug("########## user delete : success");
            return ResponseEntity.ok().build();
        } else {
            log.debug("########## user delete : fail");
            return ResponseEntity.status(401).build();
        }
    }


    /*로그인 페이지 접근*/
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "user/login";
    }

}
