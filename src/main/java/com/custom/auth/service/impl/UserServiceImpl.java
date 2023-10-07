package com.custom.auth.service.impl;

import com.custom.auth.entity.User;
import com.custom.auth.exception.BadRequestException;
import com.custom.auth.repository.UserRepository;
import com.custom.auth.service.CustomEmailService;
import com.custom.auth.service.UserService;
import com.custom.auth.util.EncDecUtil;
import com.custom.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomEmailService customEmailService;

    @Autowired
    private EncDecUtil encDecUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String saveUser(User user) {
        try {
            user.setIsActive(Boolean.FALSE);
//            user.setPassword(encDecUtil.encryptString(user.getPassword()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            return jwtUtil.createJwtToken(savedUser);
//            customEmailService.sendActivationLink(savedUser);
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
