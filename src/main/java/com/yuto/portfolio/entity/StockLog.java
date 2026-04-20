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
    private Integer itemId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}