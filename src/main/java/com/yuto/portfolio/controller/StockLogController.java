package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.StockLog;
import com.yuto.portfolio.repository.StockLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StockLogController {

    @Autowired
    private StockLogRepository stockLogRepository;

    @GetMapping("/log")
    public String viewLogs(Model model) {
        //全ての履歴を取得
        List<StockLog> logs = stockLogRepository.findAll();
        model.addAttribute("logs", logs);
        return "logs/list"; //templates/logs/list.htmlを表示
    }
}
