package com.custom.auth.controller;

import com.custom.auth.entity.User;
import com.custom.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("signUp")
    public ResponseEntity signUpUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity(userList, HttpStatus.ACCEPTED);
    }
}
