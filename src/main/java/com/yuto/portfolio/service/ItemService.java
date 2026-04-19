package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //一覧取得
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //IDで1件取得（編集用）
    public Item getItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item id:" + id));
    }

    //保存（新規も更新もこれ使う）
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    //削除
    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    // 発注の処理-----------------------------------------------------------------------

    // IDのリストから食材をまとめて取得する
    public List<Item> getItemsByIds(List<Integer> ids) {
        return itemRepository.findAllById(ids);
    }

    // 在庫不足（現在庫 <= 発注点）の食材のみを取得する
    public List<Item> getReorderAlertItems() {
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream()
                .filter(item -> item.getQuantity() <= item.getReorderPoint())
                .toList();
    }

    // ItemService.java の executeOrder を修正

    private final StockLogService stockLogService; // 注入に追加

    @Transactional
    public void executeOrder(List<Integer> itemIds, List<Integer> quantities) {
        for (int i = 0; i < itemIds.size(); i++) {
            Integer itemId = itemIds.get(i);
            Integer qty = quantities.get(i);

            Item item = itemRepository.findById(Math.toIntExact(Long.valueOf(itemId))).orElseThrow();
            item.setQuantity(item.getQuantity() + qty);
            itemRepository.save(item);

            // ★ここでログを保存！ (userIdは一旦仮で 1 にしています)
            stockLogService.addLog(itemId, 1, "入庫（発注）", qty);
        }
    }


}
