package com.example.springjpa.domain.listener;

import com.example.springjpa.domain.User;
import com.example.springjpa.domain.UserHistory;
import com.example.springjpa.repository.UserHistoryRepository;
import com.example.springjpa.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void preUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setEmail(user.getEmail());
        userHistory.setName(user.getName());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
