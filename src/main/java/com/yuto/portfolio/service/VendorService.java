package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.entity.Vendor;
import com.yuto.portfolio.repository.ItemRepository;
import com.yuto.portfolio.repository.StockLogRepository;
import com.yuto.portfolio.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    // 仕入先リスト取得用
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElseThrow();
    }

    public void saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
