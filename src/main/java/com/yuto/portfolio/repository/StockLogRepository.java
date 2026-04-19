package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockLogRepository extends JpaRepository<StockLog, Integer> {
        // 新しい順に並べて取得
        List<StockLog> findAllByOrderByCreatedAtDesc();
}