package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.Vender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenderRepository extends  JpaRepository<Vender, Long> {

}
