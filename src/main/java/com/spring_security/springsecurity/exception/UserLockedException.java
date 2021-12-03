package com.spring_security.springsecurity.exception;

public class UserLockedException extends Exception{

    public UserLockedException() {
    }

    public UserLockedException(String message) {
        super(message);
    }
}
