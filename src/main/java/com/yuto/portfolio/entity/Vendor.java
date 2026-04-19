package com.yuto.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vendors")
@Data
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName; // 仕入先名

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; //仕入先電話番号

}