package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    //発注勧告用:在庫が発注点以下のものを探すメソッド
    //List<Item> findByQuantityLessThanEqual(Integer reorderPoint);
}
