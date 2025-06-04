package com.example.demo.admin;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args){
        String adminEmail = "admin@naver.com";
        if(userRepository.findByEmail(adminEmail).isEmpty()){
            User admin = new User(
                    adminEmail,
                    passwordEncoder.encode("1234"),
                    "관리자",
                    User.Role.ADMIN
            );
            userRepository.save(admin);
            System.out.println("관리자 계정 생성됨: " + adminEmail);
        }
    }
}
