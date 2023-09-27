package com.custom.auth.service.impl;

import com.custom.auth.entity.User;
import com.custom.auth.exception.BadRequestException;
import com.custom.auth.repository.UserRepository;
import com.custom.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void saveUser(User user) {
        try {
            user.setIsActive(Boolean.FALSE);
            userRepository.save(user);
        }
        catch (DataIntegrityViolationException e){
            throw new BadRequestException("Violation of unique field"+e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
