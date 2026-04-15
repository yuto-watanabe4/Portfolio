package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockLogRepository extends JpaRepository<StockLog, Long> {
        //食材ごとに履歴を絞り込む
        List<StockLog> findByItemId(Long itemId);
}

