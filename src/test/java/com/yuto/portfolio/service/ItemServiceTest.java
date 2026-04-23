package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.repository.ItemRepository; // 追加
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("在庫更新：発注確定後に在庫が正しく増えること")
    void testExecuteOrder() {
        // 1. テスト用の食材を準備
        Item item = new Item();
        item.setItemName("テスト用牛肉");
        item.setQuantity(10);
        item.setReorderPoint(5);
        item = itemRepository.save(item); // 保存してIDを確定させる

        // 2. 実行：5個発注する
        // 型変換 Math.toIntExact(itemId) を使用
        Integer itemId = Math.toIntExact(item.getItemId());
        itemService.executeOrder(List.of(itemId), List.of(5));

        // 3. 検証：10 + 5 = 15 になっているか
        Item updatedItem = itemRepository.findById(Math.toIntExact(Long.valueOf(itemId))).orElseThrow();
        assertEquals(15, updatedItem.getQuantity());
    }

    @Test
    @DisplayName("アラート：在庫が発注点以下のアイテムのみ取得")
    void testAlertLogic() {
        // 全ての取得アイテムが「在庫 <= 発注点」であることを確認
        List<Item> alerts = itemService.getReorderAlertItems();
        alerts.forEach(item ->
                assertTrue(item.getQuantity() <= item.getReorderPoint(),
                        item.getItemName() + "の在庫が発注点を超えています")
        );
    }
}