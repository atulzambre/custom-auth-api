package com.custom.auth.service.impl;

import com.custom.auth.entity.Roles;
import com.custom.auth.entity.User;
import com.custom.auth.model.Role;
import com.custom.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FillUserDetails {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void fillUsers(){

        User user;

        for(int i=0;i<100;i++){
            Roles adminRole = new Roles();
            adminRole.setRole(Role.ADMIN);
            Roles userRole = new Roles();
            userRole.setRole(Role.USER);
            user = new User();
            user.setUsername("atul"+i);
            user.setPassword(encoder.encode("atul"+i));
            user.setIsActive(Boolean.TRUE);
            user.setEmail("atul.zambre"+i+"@gmail.com");
            user.setRoles(new HashSet<>(){{
                add(adminRole);
                add(userRole);
            }});
            userRepository.save(user);
            System.out.println("user added  username :"+user.getUsername());
        }
    }
}
