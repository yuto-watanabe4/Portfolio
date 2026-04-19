package com.yuto.portfolio.service;


import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.entity.StockLog;
import com.yuto.portfolio.repository.ItemRepository;
import com.yuto.portfolio.repository.StockLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockLogService {

    private final StockLogRepository stockLogRepository;
    private final ItemRepository itemRepository;

    // ログをすべて取得（リポジトリで定義した「新しい順」を使用）
    public List<StockLog> getAllLogs() {
        return stockLogRepository.findAllByOrderByCreatedAtDesc();
    }

    // ログを記録する汎用メソッド
    public void addLog(Integer itemId, Integer userId, String type, Integer qty) {
        StockLog log = new StockLog();
        log.setItemId(itemId);
        log.setUserId(userId);
        log.setTransactionType(type);
        log.setQuantity(qty);
        stockLogRepository.save(log);
    }
}