package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("userDto", new UserDto());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes){
        userService.register(userDto);
        redirectAttributes.addFlashAttribute("message","회원가입 성공! 로그인 해주세요.");
        return "redirect:/user/login";
    }

    // 로그인 페이지 보여주기
    @GetMapping("login")
    public String showLoginForm(){
        return "user/login";
    }

    // 비밀번호 찾기 폼 보여주기
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(@RequestParam(required = false) String success, Model model) {
        if ("true".equals(success)) {
            model.addAttribute("message", "임시 비밀번호가 이메일로 발송되었습니다.");
        }
        return "user/forgot-password";
    }

    // 비밀번호 찾기 요청 처리
    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email) {
        userService.processForgotPassword(email); // 여기서 예외 던지면 글로벌 핸들러가 처리
        return "redirect:/user/forgot-password?success=true";
    }

}
