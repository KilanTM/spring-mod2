package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping
    public Users createUser(@RequestBody Users users) {
        return repository.save(users);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users newUser) {
        Users user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(newUser.getName());
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
