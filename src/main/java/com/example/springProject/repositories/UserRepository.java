package com.example.springProject.repositories;

import com.example.springProject.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}