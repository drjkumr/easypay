package com.hexaware.easypay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.User;


public interface UserRepository extends  JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);

}
