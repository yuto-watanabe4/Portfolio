package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.User;
import com.yuto.portfolio.entity.Role;
import com.yuto.portfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") //クラス内の全メソッドをADMIN限定にする
public class UserController {

    private final UserService userService;

    //ユーザー一覧表示
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/user_list";
    }

    //新規登録画面表示
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "users/register";
    }

    //登録実行
    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") User user,
                           BindingResult result, Model model) {

        // 1. 入力チェック（@NotBlankなど）のエラーがある場合
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "users/register";
        }

        try {
            userService.registerUser(user);
        } catch (RuntimeException e) {
            // 2. 重複チェックなどのビジネスロジックエラーを画面に渡す
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("roles", Role.values());
            return "users/register";
        }

        return "redirect:/users/list";
    }

    //編集画面表示
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "users/edit";
    }

    //更新実行
    @PostMapping("/edit")
    public String update(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users/list";
    }

    //削除実行
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }

}