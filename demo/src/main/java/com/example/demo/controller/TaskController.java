package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks (returns a list of tasks)
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get a single task by ID (throws an exception if not found)
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Create a new task (defaults status to "To Do" if empty)
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        System.out.println("Received new task: " + task);

        if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
            task.setStatus("To Do");
        }

        return taskRepository.save(task);
    }

    // Update an existing task (returns updated task or throws an exception)
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());

        return taskRepository.save(task);
    }

    // Delete a task (returns nothing but throws an exception if not found)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
