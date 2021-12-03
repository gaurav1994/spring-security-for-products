package com.spring_security.springsecurity.exception;

import com.spring_security.springsecurity.entity.API_ERRORS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class CustomExceptionHandling {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFoundException(ProductNotFoundException ex){
        String errorMsg = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        List<String> details = new ArrayList<>();
        details.add("product not found");
        details.add(LocalDateTime.now().toString());
        LocalDateTime time = LocalDateTime.now();
        API_ERRORS errors = API_ERRORS.builder().errorMsg(errorMsg).status(httpStatus).details(details).timeToGenerate(time).build();
        return ResponseEntity.status(httpStatus).header("error","PRODUCT NOT FOUND EXCEPTION").body(errors);
    }

    @ExceptionHandler(MultipleUserException.class)
    public ResponseEntity<?> multipleUserException(MultipleUserException ex){
        String errorMsg = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.MULTI_STATUS;
        List<String> details = new ArrayList<>();
        details.add("Multiple user found ");
        details.add(LocalDateTime.now().toString());
        LocalDateTime time = LocalDateTime.now();
        API_ERRORS errors = API_ERRORS.builder().errorMsg(errorMsg).status(httpStatus).details(details).timeToGenerate(time).build();
        return ResponseEntity.status(httpStatus).header("error", "USERS SHOULD BE UNIQUE").body(errors);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<API_ERRORS> userNotFoundException(UserNotFoundException ex){
        String errMsg = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        List<String> details = new ArrayList<>();
        details.add("User not found");
        LocalDateTime time = LocalDateTime.now();
        API_ERRORS errors = API_ERRORS.builder().errorMsg(errMsg).status(httpStatus).details(details).timeToGenerate(time).build();
        return ResponseEntity.status(httpStatus).header("error","USER NOT FOUND").body(errors);
    }

    @ExceptionHandler(UserLockedException.class)
    public ResponseEntity<API_ERRORS> userLockedException(UserLockedException ex){
        String errMsg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.LOCKED).header("error", "User Locked")
                .body(API_ERRORS.builder()
                        .status(HttpStatus.LOCKED)
                        .errorMsg(errMsg)
                        .timeToGenerate(LocalDateTime.now())
                              .build()
                );
    }
}
