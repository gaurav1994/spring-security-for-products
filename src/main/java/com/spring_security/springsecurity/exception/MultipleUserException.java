package com.spring_security.springsecurity.exception;


public class MultipleUserException extends Exception{

    public MultipleUserException() {
    }

    public MultipleUserException(String message) {
        super(message);
    }
}
