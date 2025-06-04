package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, Principal principal){ // Spring Security에서 가져온 로그인한 email
        if(principal != null){
            String email = principal.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(()-> new RuntimeException("사용자 없음"));
            model.addAttribute("userName", user.getName()); // 엔티티의 name 필드 꺼냄
        }
        return "home";
    }
}
