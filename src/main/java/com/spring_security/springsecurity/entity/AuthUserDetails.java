package com.spring_security.springsecurity.entity;

import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

public class AuthUserDetails implements UserDetails {

    User user;

    public AuthUserDetails(User user) {
        this.user = user;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authoritySet = new ArrayList<>();
////        String[] rolelist = user.getRoles().split(",");
////        Arrays.stream(rolelist).forEach(e ->{
////            authoritySet.add(new SimpleGrantedAuthority("ROLE_"+e));
////        });
//        authoritySet.add(new SimpleGrantedAuthority(this.user.getRoles()));
//        System.out.println(authoritySet);
//        return authoritySet;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Role role = this.user.getRole();
        System.out.println(role);
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(this.user.getRole().getRoleName()));
        System.out.println(set);
        return set;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
