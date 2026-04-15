package com.yuto.portfolio.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username; //ログインID

    @Column(nullable = false)
    private String password; //暗号化されたパスワード

    private String role; //　権限（ADMIN、USER）
}
