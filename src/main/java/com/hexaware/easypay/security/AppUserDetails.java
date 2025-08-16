package com.hexaware.easypay.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.hexaware.easypay.entity.User;

//Basically accessing the user table from the entities and getting empid, username and password

public class AppUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final int empId;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public AppUserDetails(User user) {
        this.empId = user.getEmpId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        String role = "ROLE_" + user.getAccessLevel().toUpperCase();  //Taking role from DB and making it uppercase and appending it
        this.authorities = List.of(new SimpleGrantedAuthority(role));
    }

    public int getEmpId() {
        return empId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
