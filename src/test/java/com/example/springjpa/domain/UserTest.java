package com.example.springjpa.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test(){
        User user = new User();
        user.setEmail("lee@hanmail.net");
        user.setName("martin");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User user1 = new User(2L, "kim", "kim@gmail.com", LocalDateTime.now(), LocalDateTime.now());
        User user2 = new User("martin", "martin@naver.com");

        User user3 = User.builder()
                .name("marti")
                .email("marti@naver.com")
                .build();

        System.out.println(">>> " + user.toString());
    }
}