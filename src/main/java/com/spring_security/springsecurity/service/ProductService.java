package com.spring_security.springsecurity.service;

import com.spring_security.springsecurity.exception.ProductNotFoundException;
import com.spring_security.springsecurity.entity.Product;

import java.util.List;

public interface ProductService {

    String addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(int id) throws ProductNotFoundException;

    List<Product> getProductByAddedBy();

    String deleteProductById(int id);

    Product updateProductById(int id,Product product) throws ProductNotFoundException;
}
