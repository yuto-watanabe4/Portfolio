package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    //権限
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // ADMIN, USER などのENUM

    //ユーザー名
    @NotBlank(message = "ユーザー名は必須です")
    @Size(max = 255)
    @Column(unique = true, nullable = false, name = "user_name")
    private String username;

    //パスワード
    @NotBlank(message = "パスワードは必須です")
    @Size(min = 8, max = 255, message = "パスワードは8文字以上で入力してください")
    @Column(nullable = false)
    private String password;

    //アカウント作成日
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //アカウント作成日を自動でセットします
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}