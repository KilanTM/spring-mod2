package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // This creates a foreign key column in the task table for the user relationship
    private Users users;

    @ManyToOne
    private Project project;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Task() {} // Default constructor (needed for JPA)

    // Constructor for tasks without a user
    public Task(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Constructor for tasks with a user
    public Task(String name, String description, String status, Users users) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.users = users; // Set the user
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Users getUser() { return users; }
    public void setUser(Users users) { this.users = users; }
}
