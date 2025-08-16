package com.hexaware.easypay.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final String secret;

    public JwtAuthFilter(String secret) {
        this.secret = secret;
    }

    // Token Validation Logic 
    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

                //Validation and parsing of the token
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            //Removing the bearer from the token
            String token = header.substring(7);
            //Checking for claims
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                        //Validating the Claims info and verifying the identity
                String username = claims.getSubject();
                Integer empId = claims.get("empId", Integer.class);
                List<String> roles = claims.get("roles", List.class);

                var authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                var principal = new org.springframework.security.core.userdetails.User(username, "", authorities);
                var authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);
                request.setAttribute("empId", empId);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }
}
