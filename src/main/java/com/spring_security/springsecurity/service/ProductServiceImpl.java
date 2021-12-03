package com.spring_security.springsecurity.service;

import com.spring_security.springsecurity.entity.AuthUserDetails;
import com.spring_security.springsecurity.entity.Product;
import com.spring_security.springsecurity.exception.ProductNotFoundException;
import com.spring_security.springsecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;


    @Override
    public String addProduct(Product product) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails currentUser = (AuthUserDetails) authentication.getPrincipal();

        product.setAddedby(currentUser.getUsername());
        Collection roles = currentUser.getAuthorities();
        String role = "ADMIN";
        for (Object c : roles){
            System.out.println(c);
            role = c.toString().substring(5);
        }
        product.setAddedbyLevel(role);
        productRepository.save(product);
        return "Product Added Successfully";
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
        System.out.println(product);
        return product;
    }

    @Override
    public List<Product> getProductByAddedBy() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails currentLoginUser = (AuthUserDetails) authentication.getPrincipal();
        String empName = currentLoginUser.getUsername();
        List<Product> productList;
        if(empName.equals("admin")){
            productList = getAllProducts();
        }else{
            productList = productRepository.findByAddedby(empName).stream().collect(Collectors.toList());
        }
        return productList;
    }

    @Override
    public String deleteProductById(int id){

//        for(Product p : productList){
//            if(p.getId() == id){
//                productList.remove(p);
//            }else{
//                return "Product with this id is not found";
//            }
//        }

        productRepository.deleteById(id);
        return "Product Deleted Successfully";
    }

    @Override
    public Product updateProductById(int id, Product product) throws ProductNotFoundException {
        Product productProcess = productRepository.getById(id);
        if(productProcess.getId() == product.getId()) {
            productProcess = product;
            productRepository.save(productProcess);
        }else {
            throw new ProductNotFoundException("Product not found with this id");
        }
        return productProcess;
    }
}
