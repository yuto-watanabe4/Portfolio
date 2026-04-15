package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String dashboard(Model model){
        //在庫不足の食材だけを取得（ItemServiceに作ったメソッドを利用）
        List<Item> lowStockItems = itemService.getLowStockItems();
        model.addAttribute("lowStockItems", lowStockItems);

        //全体の食材数を表示
        model.addAttribute("totalItems", itemService.getAllItems().size());

        return "dashboard";//dashboard.htmlに表示
    }
}
