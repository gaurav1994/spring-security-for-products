package com.spring_security.springsecurity.filter;

import com.spring_security.springsecurity.helper.JwtUtils;
import com.spring_security.springsecurity.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerHeader = request.getHeader("Authorization");
        String username = "";

        if(bearerHeader != null && bearerHeader.startsWith("Bearer ")){
            String token = bearerHeader.substring(7);
            try{
                username = jwtUtils.extractUsername(token);
            }catch (Exception e){
                e.printStackTrace();
            }
            UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);
            if(jwtUtils.validateToken(token,userDetails)){
                System.out.println("Token authenticated congretus");
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            else {
                System.out.println("Token is not Authenticated ");
            }
        }
        filterChain.doFilter(request,response);
    }
}
