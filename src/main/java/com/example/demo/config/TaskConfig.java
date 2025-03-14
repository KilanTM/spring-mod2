package com.example.demo.config;

import com.example.demo.model.Task;
import com.example.demo.model.Users;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskConfig {
    @Bean
    CommandLineRunner taskCommandLineRunner(TaskRepository taskRepository, UserRepository userRepository) {
        return args -> {
            // Creating a sample user
            Users users = new Users("JohnDoe");
            userRepository.save(users);

            // Creating sample tasks and associating them with the user
            Task task1 = new Task("Clean Room", "Tidy up the bedroom", "TODO", users);
            Task task2 = new Task("Write Report", "Finish the weekly report", "IN_PROGRESS", users);
            Task task3 = new Task("Exercise", "Go for a 30-minute run", "DONE", users);

            // Save tasks to the database
            taskRepository.saveAll(List.of(task1, task2, task3));
        };
    }
}
