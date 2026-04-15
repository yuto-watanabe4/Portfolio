package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //特定のステータス（PENDING/発注中)のものだけを探すときに使う
    List<Order> findByStatus(String status);
}
