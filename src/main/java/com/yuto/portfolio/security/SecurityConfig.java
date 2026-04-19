package com.yuto.portfolio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() //ログイン画面は見れるようにする
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")               // このHTML（/login）を表示する
                        .defaultSuccessUrl("/items", true) // 成功したら一覧へ
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 【テスト用】ハッシュ化せず、入力された文字をそのまま比較する
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }
}