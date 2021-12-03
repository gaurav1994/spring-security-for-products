package com.spring_security.springsecurity.service;

import com.spring_security.springsecurity.entity.AuthUserDetails;
import com.spring_security.springsecurity.entity.User;
import com.spring_security.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with these credentials");
        }
        AuthUserDetails authUserDetails = new AuthUserDetails(user);
        return authUserDetails;
    }
}
