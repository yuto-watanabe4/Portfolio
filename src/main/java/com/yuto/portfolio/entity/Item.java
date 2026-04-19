package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items") //itemsテーブル
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column(nullable = false)
    private String itemName; //食材名

    @Column(nullable = false)
    private Integer reorderPoint = 0; //発注点

    @Column(nullable = false)
    private Integer price = 0; //価格

    @Column(nullable = false)
    private Integer quantity = 0; //現在在庫数

    // categoryId と venderId は、後で @ManyToOne に書き換えますが
    // 一旦 Integer で定義しておくとスムーズに起動できます
    private Integer categoryId;
    private Integer venderId;
}
