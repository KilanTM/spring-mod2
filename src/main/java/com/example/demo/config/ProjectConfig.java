package com.example.demo.config;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    CommandLineRunner projectCommandLineRunner(ProjectRepository repository) {
        return args -> {
            Project website = new Project("Website Redesign");
            Project marketing = new Project("Marketing Campaign");

            repository.saveAll(List.of(website, marketing));
        };
    }
}
