package com.spring_security.springsecurity.repository;

import com.spring_security.springsecurity.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findByAddedby(String empName);
}
