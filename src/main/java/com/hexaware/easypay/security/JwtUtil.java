package com.hexaware.easypay.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiry-seconds:3600}") //JWT Token expires in 1hr
    private long expirySeconds;

    @SuppressWarnings("deprecation")
    //Warning Supression for deprecated (but working) methods
    public String generateToken(String username, int empId, List<String> roles) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plusSeconds(expirySeconds));
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .claim("empId", empId)
            .claim("roles", roles)
            .signWith(SignatureAlgorithm.HS256, key)
            .compact();

    }
}
