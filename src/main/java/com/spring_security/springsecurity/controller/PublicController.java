package com.spring_security.springsecurity.controller;

import com.spring_security.springsecurity.entity.Role;
import com.spring_security.springsecurity.entity.User;
import com.spring_security.springsecurity.exception.MultipleUserException;
import com.spring_security.springsecurity.repository.RoleRepository;
import com.spring_security.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    public static final String user_role = "ROLE_USER";

    @PostMapping("/user-registration")
    public ResponseEntity<User> createUser(@RequestBody User user) throws MultipleUserException {

        user.setRole(roleRepository.findByRoleName(user_role));
        User fetchedUser = userService.userCreation(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", "USER REGISTRATION API");
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(fetchedUser);
    }
}
