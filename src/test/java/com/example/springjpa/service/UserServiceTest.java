package com.example.springjpa.service;

import com.example.springjpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        userService.put();

        System.out.println(">>> " + userRepository.findByEmail("user@naver.com"));
        userRepository.findAll().forEach(System.out::println);
    }


}