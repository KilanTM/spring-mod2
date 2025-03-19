package com.example.demo.config;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository) {
        return args -> {
            Users alice = new Users("Alice");
            Users bob = new Users("Bob");

            repository.saveAll(List.of(alice, bob));
        };
    }
}
