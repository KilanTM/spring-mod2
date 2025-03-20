package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository repository;

    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    // Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    // Get a single project by ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Create a new project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return repository.save(project);
    }

    // Update a project
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Project project = repository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(updatedProject.getName());
        return repository.save(project);
    }


    // Delete a project
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
