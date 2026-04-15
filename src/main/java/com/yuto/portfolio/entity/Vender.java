package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Venders") //vendersテーブル
@Data
public class Vender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vender_id")
    private Long venderId;

    @Column(name = "Vender_name", nullable = false)
    private String venderName;

    @Column(name = "phone_number")
    private String phoneNumber;
}
