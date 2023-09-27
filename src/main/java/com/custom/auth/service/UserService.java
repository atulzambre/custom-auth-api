package com.custom.auth.service;

import com.custom.auth.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getAllUsers();
}
