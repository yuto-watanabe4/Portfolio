package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_log")
@Data
public class StockLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LogId;

    // どの食材の履歴か（Itemとの紐付け）
    private Long itemId;

    // 誰が操作したか（本来はUserと紐付けますが、一旦数値か名前で）
    private Long userId;

    // 入庫、出庫、廃棄などの種別
    private String transactionType;

    // 動かした数量
    private Integer quantity;

    // いつ起きたか
    private LocalDateTime occurredAt;

    // 保存する直前に、現在時刻を自動セットする
    @PrePersist
    protected void onCreate() {
        occurredAt = LocalDateTime.now();
    }
}

