package com.yuto.portfolio.service;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.entity.StockLog;
import com.yuto.portfolio.repository.ItemRepository;
import com.yuto.portfolio.repository.StockLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    //すべての食材を取得
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //新しい食材を保存するメソッド
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    //在庫不足の食材をリストアップ
    public List<Item> getLowStockItems() {
        //DBから全データ取得
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream().filter(item -> item.getQuantity() < item.getReorderPoint()).toList();
    }

    @Autowired
    private StockLogRepository stockLogRepository;

    //在庫を変動させて、履歴に残すメソッド
    @Transactional //両方の処理が成功したときだけ確定させる
    public void updateStockWithLog(Long itemId, Integer changeAmount, String type, Long userId) {
        //1.現在個の修正
        Item item = itemRepository.findById(itemId).orElseThrow();
        item.setQuantity(item.getQuantity() + changeAmount);
        itemRepository.save(item);

        //2.履歴（ログ）の作成
        StockLog log = new StockLog();
        log.setItemId(itemId);
        log.setQuantity(changeAmount);
        log.setTransactionType(type);
        log.setUserId(userId);
        stockLogRepository.save(log);

    }

}
