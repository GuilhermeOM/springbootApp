package com.example.springProject.config;

import java.util.Arrays;
import com.example.springProject.entities.User;
import com.example.springProject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Test1", "test1@gmail.com", "12345-1", "test1&123");
        User u2 = new User(null, "Test2", "test2@gmail.com", "12345-2", "test2&123");
    
        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}