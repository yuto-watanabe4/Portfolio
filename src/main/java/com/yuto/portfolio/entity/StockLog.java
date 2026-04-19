package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_logs")
@Data
public class StockLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(nullable = false)
    private Integer itemId; // どの食材か

    @Column(nullable = false)
    private Integer userId; // 誰が操作したか

    @Column(nullable = false)
    private String transactionType; // 入庫, 出庫, 廃棄, 棚卸調整

    @Column(nullable = false)
    private Integer quantity; // 動かした数量

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}