package com.custom.auth.controller;

import com.custom.auth.entity.User;
import com.custom.auth.repository.UserRepository;
import com.custom.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signUp")
    public ResponseEntity signUpUser(@RequestBody User user) {
        return new ResponseEntity(userService.saveUser(user),HttpStatus.ACCEPTED);
    }

    @GetMapping("admin/users")
    public ResponseEntity getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }
}
