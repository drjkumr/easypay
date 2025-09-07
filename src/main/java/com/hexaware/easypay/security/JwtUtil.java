package com.hexaware.easypay.security;

import java.nio.charset.StandardCharsets;
<<<<<<< HEAD
import java.time.Instant;
import java.util.Date;
import java.util.List;
=======
import java.util.Date;
>>>>>>> 314dc4c (Updated latest backend)

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
=======
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
>>>>>>> 314dc4c (Updated latest backend)

@Component
public class JwtUtil {

<<<<<<< HEAD
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
=======
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().iterator().next().getAuthority())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

>>>>>>> 314dc4c (Updated latest backend)
}
