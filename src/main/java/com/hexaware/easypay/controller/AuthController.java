package com.hexaware.easypay.controller;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.security.AppUserDetails;
import com.hexaware.easypay.security.JwtUtil;

//Used for auhenticating the user and access their respective services

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public static record LoginRequest(String username, String password) {
    }

    public static record LoginResponse(String accessToken, String tokenType, long expiresIn) {
    }

    //Main login method
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        var auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        var principal = (AppUserDetails) auth.getPrincipal();
        var roles = principal.getAuthorities().stream().map(a -> a.getAuthority()).toList();
        String token = jwtUtil.generateToken(principal.getUsername(), principal.getEmpId(), roles);
        return new LoginResponse(token, "Bearer", 3600);
    }

    //Get current user info
    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal UserDetails ud) {
        AppUserDetails a = (AppUserDetails) ud;
        return Map.of(
                "username", a.getUsername(),
                "empId", a.getEmpId(),
                "roles", a.getAuthorities().stream().map(x -> x.getAuthority()).toList());
    }
}
