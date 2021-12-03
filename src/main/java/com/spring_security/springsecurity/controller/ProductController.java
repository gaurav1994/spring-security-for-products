package com.spring_security.springsecurity.controller;

import com.spring_security.springsecurity.entity.Product;
import com.spring_security.springsecurity.entity.SimpleMessage;
import com.spring_security.springsecurity.exception.ProductNotFoundException;
import com.spring_security.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin( origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/greet")
    public ResponseEntity<SimpleMessage> greetMsg(){
        return ResponseEntity.ok().body(new SimpleMessage("This is simple greet message"));
    }

    @PostMapping("/add-product")
    public ResponseEntity<SimpleMessage> addProduct(@RequestBody Product product){
        String fetchedMsg = productService.addProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc","PRODUCT ADDED");
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(new SimpleMessage(fetchedMsg));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc","GET ALL PRODUCT LIST");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productList);
    }

    @GetMapping("/get-all/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) throws ProductNotFoundException {

            Product product = productService.getProductById(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", "GET SINGLE PRODUCT BY ID");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(product);
    }

    @GetMapping("/get-all-auth-products")
    public ResponseEntity<List<Product>> getProductsByAddedByEmp(){

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByAddedBy());
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<SimpleMessage> deleteProduct(@PathVariable int id){
        String msg = productService.deleteProductById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc","DELETE SINGLE PRODUCT BY PRODUCT ID");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new SimpleMessage(msg));
    }

    @PatchMapping("/update-product-by-id/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) throws ProductNotFoundException{
        Product productFetched = productService.updateProductById(id,product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
