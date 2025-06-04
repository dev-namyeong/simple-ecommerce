package com.example.demo.user;

import com.example.demo.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void register(UserDto dto){
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }

        User user = new User(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getName(),
                User.Role.USER
        );
        userRepository.save(user);
    }

    public void processForgotPassword(String email){
        // 1. 이메일로 회원 조회 (없으면 예외 발생)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입된 이메일이 없습니다."));

        // 2. 임시 비밀번호 생성 (UUID를 이용해서 10글자 잘라서 사용)
        String tempPassword = UUID.randomUUID().toString().substring(0,10); // 임시비번
        // 3. 생성한 임시 비밀번호를 암호화애서 user에 저장
        user.changePassword(passwordEncoder.encode(tempPassword)); // 비밀번호 암호화
        // 4. 바뀐 내용 저장 (DB 반영)
        userRepository.save(user);
        // 5. 사용자 이메일로 인시 비밀번호 저장
        mailService.sendTempPasswordEmail(user.getEmail(),tempPassword); // 메일 발송
    }
}
