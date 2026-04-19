package com.yuto.portfolio.repository;

import com.yuto.portfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
        //ユーザー名でユーザー検索
        Optional<User> findByUsername(String username);

        //指定したユーザー名が存在するか確認
        boolean existsByUsername(String username);
}
