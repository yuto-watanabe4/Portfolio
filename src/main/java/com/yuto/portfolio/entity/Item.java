package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "items") //itemsテーブル
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "食材名は必須です") //空文字禁止
    @Size(max = 255)
    //@Column(nullable = false)
    private String itemName;

    @Min(value = 0, message = "在庫数は0以上で入力してください") // マイナス禁止
    @NotNull(message = "現在の在庫数を入力してください")
    //@Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String unit;

    @Min(value = 0, message = "発注点は0以上で入力してください")
    @NotNull(message = "発注点を入力してください")
    //@Column(nullable = false)
    private Integer reorderPoint;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "vender_id")
    private Vender vender;
}
