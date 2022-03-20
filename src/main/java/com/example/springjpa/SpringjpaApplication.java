package com.example.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringjpaApplication.class, args);
    }

}
