package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Role;
import com.yuto.portfolio.entity.User;
import com.yuto.portfolio.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("新規登録：パスワードが入力した通りに保存されること")
    void testRegisterWithoutHash() {
        // 準備
        User user = new User();
        user.setUsername("test_user_raw");
        user.setPassword("password123"); // これがそのまま保存されるか確認
        user.setRole(Role.USER);

        // 実行
        userService.registerUser(user);

        // 検証
        User savedUser = userRepository.findByUsername("test_user_raw").orElseThrow();

        // 一致することを期待するので assertEquals を使う
        assertEquals("password123", savedUser.getPassword(), "パスワードが生のまま保存されている必要があります");
        assertEquals(Role.USER, savedUser.getRole());
    }
}