package com.custom.auth.service;

import com.custom.auth.entity.User;

public interface CustomEmailService {
    void sendActivationLink(User savedUser);
}
