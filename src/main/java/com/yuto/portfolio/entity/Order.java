package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "vender_id")
    private Vender vender;

    private Integer orderQuantity;
    private String status; //"PENDING（発注中), "COMPLETED"（入荷中）
    private LocalDateTime orderedAt;

    @PrePersist
    protected void onCreate() {
        orderedAt = LocalDateTime.now();
        if (status == null) status = "PENDING";
    }
}
