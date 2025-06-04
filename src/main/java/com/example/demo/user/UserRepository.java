package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // 로그인 때 사용자 찾기용

    boolean existsByEmail(String email); // 이메일 중복 확인용
}
