package com.spring_security.springsecurity.repository;

import com.spring_security.springsecurity.entity.LikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikedProductRepository extends JpaRepository<LikedProduct,Integer> {

    @Query(value = "select * from liked_product_tbl where username=?1",nativeQuery = true)
    List<LikedProduct> findByUserId(String username);
}
