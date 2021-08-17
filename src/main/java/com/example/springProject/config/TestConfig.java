package com.example.springProject.config;

import com.example.springProject.entities.Category;
import com.example.springProject.entities.Order;
import java.util.Arrays;
import com.example.springProject.entities.User;
import com.example.springProject.entities.enums.OrderStatus;
import com.example.springProject.repositories.CategoryRepository;
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
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Category cat1 = new Category(null, "Eletronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");
        
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        
        User u1 = new User(null, "Test1", "test1@gmail.com", "12345-1", "test1&123");
        User u2 = new User(null, "Test2", "test2@gmail.com", "12345-2", "test2&123");
        
        Order o1 = new Order(null, Instant.parse("2021-01-10T13:10:00Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2021-02-11T14:20:30Z"), OrderStatus.DELIVERED, u2);
        Order o3 = new Order(null, Instant.parse("2021-01-20T15:30:40Z"), OrderStatus.SHIPPED, u1);
    
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}