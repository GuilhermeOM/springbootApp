package com.example.springProject.services;

import com.example.springProject.entities.User;
import com.example.springProject.repositories.UserRepository;
import com.example.springProject.services.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    
    public List<User> findAll(){
        return repository.findAll();
    }
    
    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public User Insert(User obj){
        return repository.save(obj);
    }
    
    public void DeleteById(Long id){
        repository.deleteById(id);
    }
    
    public User update(Long id, User obj){
        User entity = repository.getOne(id);
        updateDate(entity, obj);
        return repository.save(entity);
    }

    private void updateDate(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
