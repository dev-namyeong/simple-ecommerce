package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/check-email")
    public Map<String, Boolean> checkEmail(@RequestParam String email){
        System.out.println("[DEBUG] checkEmail 호출됨: " + email);
        boolean exists = userRepository.existsByEmail(email);
        System.out.println("[DEBUG] exists 결과: " + exists);
        return Map.of("exists", exists);
    }
}
