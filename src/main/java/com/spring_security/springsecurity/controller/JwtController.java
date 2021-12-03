package com.spring_security.springsecurity.controller;

import com.spring_security.springsecurity.exception.UserLockedException;
import com.spring_security.springsecurity.exception.UserNotFoundException;
import com.spring_security.springsecurity.helper.JwtUtils;
import com.spring_security.springsecurity.model.JwtRequest;
import com.spring_security.springsecurity.model.JwtResponse;
import com.spring_security.springsecurity.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthUserDetailsService authUserDetailsService;

    @PostMapping("/token")
    public ResponseEntity<?> tokenAccess(@RequestBody JwtRequest request) throws UserLockedException, UserNotFoundException {

//        UserDetails userDetails = null;
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        }catch (DisabledException e){
            e.printStackTrace();
            throw new UserLockedException("User is Locked");
        }
        catch (BadCredentialsException e){
            e.printStackTrace();
            throw new UserNotFoundException("Invalid Cradentials");
        }
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(request.getUsername());
        if(!userDetails.isEnabled()) {
            throw new UserLockedException("User is Locked");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        Object role = authorities.toArray()[0];

        String token = jwtUtils.generateToken(userDetails);

        JwtResponse responseToken = new JwtResponse(token,role.toString(),userDetails.getUsername());
        System.out.println(responseToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+token);
        httpHeaders.add("Role",role.toString());
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(responseToken);
    }
}
