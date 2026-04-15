package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
                            //保存・検索・削除
}
