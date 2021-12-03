package com.spring_security.springsecurity.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
