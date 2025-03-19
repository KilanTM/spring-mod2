package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @JsonProperty
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(mappedBy = "projects")
    private Set<Users> users = new HashSet<>(); // Renamed to match Users.java

    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Task> getTasks() { return tasks; }
    public void setTasks(Set<Task> tasks) { this.tasks = tasks; }

    public Set<Users> getUsers() { return users; }  // Add getter
    public void setUsers(Set<Users> users) { this.users = users; }  // Add setter
}
