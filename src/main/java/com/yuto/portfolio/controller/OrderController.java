package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.service.ItemService;
import com.yuto.portfolio.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final VendorService vendorService;

    // 1. 発注メニュー画面 (各HTMLの /orders へのリンク先)
    @GetMapping
    public String orderMenu() {

        return "orders/menu";
    }

    // 2. 通常発注：食材選択画面
    @GetMapping("/select")
    public String selectForm(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "orders/select";
    }

    // 3. 発注勧告画面
    @GetMapping("/alert")
    public String alertForm(Model model) {
        model.addAttribute("alertItems", itemService.getReorderAlertItems());
        return "orders/alert";
    }

    // 4. 確認画面（通常・勧告 共通）
    @PostMapping({"/confirm", "/alert/select"})
    public String confirmOrder(@RequestParam(value = "itemIds", required = false) List<Integer> itemIds, Model model) {
        if (itemIds == null || itemIds.isEmpty()) {
            return "redirect:/orders/select"; // 選択なしなら戻す
        }
        model.addAttribute("selectedItems", itemService.getItemsByIds(itemIds));
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "orders/confirm";
    }

    // 5. 発注完了処理
    @PostMapping("/complete")
    public String completeOrder(
            @RequestParam("itemIds") List<Integer> itemIds,
            @RequestParam("quantities") List<Integer> quantities) {

        // 在庫を増やす処理などをサービスで実行
        itemService.executeOrder(itemIds, quantities);

        return "orders/complete";
    }
}