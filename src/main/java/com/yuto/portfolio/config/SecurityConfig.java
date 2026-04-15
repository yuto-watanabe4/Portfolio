package com.yuto.portfolio.config;

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
                        // 1. ログイン画面や静的ファイルは誰でも見れるようにする
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                        // 2. それ以外はすべて認証が必要
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")               // 自作のログインページを使う
                        .defaultSuccessUrl("/", true)      // 成功したらダッシュボードへ
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // ログアウトしたらログイン画面へ
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}