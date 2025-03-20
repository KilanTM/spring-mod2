package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.model.Task;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final TaskRepository taskRepository;

    public UserController(UserRepository repository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
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
    @PutMapping("/{id}/projects/{pid}")
    public Users addProject(@PathVariable Long id, @PathVariable Long pid) {
        Users user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Project project = projectRepository.findById(pid).orElseThrow(() -> new RuntimeException("Project not found"));
        user.getProjects().add(project);
        return repository.save(user);
    }
    @PutMapping("/{id}/tasks/{tid}")
    public Users addTask(@PathVariable Long id, @PathVariable Long tid) {
        Users user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepository.findById(tid).orElseThrow(() -> new RuntimeException("Task not found"));
        user.getTasks().add(task);
        task.setUser(user);
        taskRepository.save(task);
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
