package com.custom.auth.controller;

import com.custom.auth.entity.User;
import com.custom.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signUp")
    public ResponseEntity signUpUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("signIn")
    public ResponseEntity signInUser(@RequestBody User user) {
        return new ResponseEntity(userService.signIn(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("admin/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("/activateUser/{token}")
    public ResponseEntity activateUser(@PathVariable("token") String activationToken) {
        userService.activateUser(activationToken);
        return new ResponseEntity("Account Activation: Successful", HttpStatus.OK);
    }
}
