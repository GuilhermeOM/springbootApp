package com.example.springProject.config;

import com.example.springProject.entities.Order;
import java.util.Arrays;
import com.example.springProject.entities.User;
import com.example.springProject.repositories.OrderRepository;
import com.example.springProject.repositories.UserRepository;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Test1", "test1@gmail.com", "12345-1", "test1&123");
        User u2 = new User(null, "Test2", "test2@gmail.com", "12345-2", "test2&123");
        
        Order o1 = new Order(null, Instant.parse("2021-01-10T13:10:00Z"), u1);
        Order o2 = new Order(null, Instant.parse("2021-02-11T14:20:30Z"), u2);
        Order o3 = new Order(null, Instant.parse("2021-01-20T15:30:40Z"), u1);
    
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}