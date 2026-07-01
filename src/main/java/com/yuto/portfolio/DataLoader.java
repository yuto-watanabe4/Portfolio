package com.yuto.portfolio;

import com.yuto.portfolio.entity.Role;
import com.yuto.portfolio.entity.User;
import com.yuto.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // user_name（username）で重複チェック
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();

            // エンティティのフィールドに合わせてセット
            admin.setRole(Role.ADMIN);
            admin.setUsername("admin");
            admin.setPassword("password123");

            userRepository.save(admin);
        }
    }
}
