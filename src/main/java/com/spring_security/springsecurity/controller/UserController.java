package com.spring_security.springsecurity.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/userhome")
    public String getUser(){
        return "user controller";
    }
}
