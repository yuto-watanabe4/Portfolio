package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Vendor;
import com.yuto.portfolio.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    // 一覧表示
    @GetMapping
    public String listVendors(Model model) {
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "vendors/list";
    }

    // 新規登録画面
    @GetMapping("/new")
    public String newVendorForm(Model model) {
        model.addAttribute("vendor", new Vendor()); // 空のオブジェクト
        return "vendors/form";
    }

    // 編集画面
    @GetMapping("/edit/{id}")
    public String editVendorForm(@PathVariable Long id, Model model) {
        model.addAttribute("vendor", vendorService.getVendorById(id));
        return "vendors/form";
    }

    // 保存処理（新規・編集共通）
    @PostMapping("/save")
    public String saveVendor(@ModelAttribute Vendor vendor) {
        vendorService.saveVendor(vendor);
        return "redirect:/vendors";
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return "redirect:/vendors";
    }
}