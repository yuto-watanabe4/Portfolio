package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Category;
import com.yuto.portfolio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    //カテゴリ一覧と登録フォームを表示
    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("category", new Category()); //登録用の空箱
        return "categories/list";
    }

    //カテゴリの保存
    @PostMapping("/categories/register")
    public String registerCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}

