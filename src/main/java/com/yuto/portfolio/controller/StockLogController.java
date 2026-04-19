package com.yuto.portfolio.controller;

import com.yuto.portfolio.service.ItemService;
import com.yuto.portfolio.service.StockLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logs")
@RequiredArgsConstructor
public class StockLogController {

    private final StockLogService stockLogService;
    private final ItemService itemService;

    @GetMapping
    public String listLogs(Model model) {
        model.addAttribute("logs", stockLogService.getAllLogs());
        // IDから名前を引けるように、食材リストも渡しておくとHTMLで便利です
        model.addAttribute("items", itemService.getAllItems());
        return "logs/list";
    }
}
