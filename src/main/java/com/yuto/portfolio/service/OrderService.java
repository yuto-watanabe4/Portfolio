package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.entity.Order;
import com.yuto.portfolio.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 発注データをDBに保存する
     */
    @Transactional
    public void placeOrder(Item item, Integer quantity) {
        Order order = new Order();
        order.setItem(item);
        // Itemが持っているVenderをそのままセット
        order.setVender(item.getVender());
        order.setOrderQuantity(quantity);
        // ステータスを「発注中」にして保存
        order.setStatus("PENDING");

        orderRepository.save(order);

        System.out.println("DEBUG: " + item.getItemName() + " を " + quantity + " 個発注登録しました。");
    }
}