package com.example.demo.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {USER,ADMIN}

    public User(String email, String password, String name, Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }


    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public void updateName(String newName){
        this.name = newName;
    }


}
