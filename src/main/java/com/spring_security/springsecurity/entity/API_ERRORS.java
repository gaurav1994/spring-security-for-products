package com.spring_security.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class API_ERRORS {
    String errorMsg ;
    HttpStatus status;
    LocalDateTime timeToGenerate;
//    int code;
    List<String> details;
}
