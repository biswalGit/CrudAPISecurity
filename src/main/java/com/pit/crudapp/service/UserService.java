package com.pit.crudapp.service;

import com.pit.crudapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pit.crudapp.entity.User;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }
}
