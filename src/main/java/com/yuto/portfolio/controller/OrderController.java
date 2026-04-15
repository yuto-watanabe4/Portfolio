package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.entity.Order;
import com.yuto.portfolio.repository.ItemRepository;
import com.yuto.portfolio.repository.OrderRepository;
import com.yuto.portfolio.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 発注履歴の一覧を表示する
     */
    @GetMapping
    public String listOrders(Model model) {
        // 全ての発注データを取得して画面に渡す
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    /**
     * 発注画面を表示する
     */
    @GetMapping("/new/{itemId}")
    public String showOrderForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + itemId));
        model.addAttribute("item", item);
        return "orders/order_form";
    }

    /**
     * 発注処理を実行する
     */
    @PostMapping("/place")
    public String placeOrder(@RequestParam Long itemId, @RequestParam Integer quantity) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + itemId));

        orderService.placeOrder(item, quantity);

        // 完了したら発注履歴一覧へリダイレクト
        return "redirect:/orders";
    }
}