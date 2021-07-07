package com.example.demo.service;

import com.example.demo.bean.User;

public interface UserService {
    Iterable<User> findAll();

    void save(User user);
}
