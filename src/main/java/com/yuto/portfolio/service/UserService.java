package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Role;
import com.yuto.portfolio.entity.User;
import com.yuto.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // コンストラクタ注入を自動生成（Lombok）
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //新規ユーザー登録
    public void registerUser(User user) {
        // 1. ユーザー名の重複チェック
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("このユーザー名は既に存在します。");
        }

        // 2. パスワードをハッシュ化してセット
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 3. デフォルト権限（一般ユーザー）を付与
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        userRepository.save(user);
    }

    //全ユーザー取得
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //IDによるユーザー取得（編集画面用）
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    //ユーザー情報更新
    public void updateUser(User user) {
        // 既存の情報を取得
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません。"));

        // ユーザー名の変更がある場合の重複チェック
        if (!existingUser.getUsername().equals(user.getUsername()) &&
                userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("変更後のユーザー名は既に使われています。");
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());

        // パスワードが入力されている場合のみ更新（空なら変更しない）
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(existingUser);
    }

    //ユーザーの削除
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}