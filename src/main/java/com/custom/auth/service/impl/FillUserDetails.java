//package com.custom.auth.service.impl;
//
//import com.custom.auth.entity.Roles;
//import com.custom.auth.entity.User;
//import com.custom.auth.model.Role;
//import com.custom.auth.repository.UserRepository;
//import com.custom.auth.service.UserService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//
//@Component
//public class FillUserDetails {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Autowired
//    private UserService userService;
//
//    @PostConstruct
//    public void fillUsers() {
//
//        User user;
//
//        for (int i = 0; i < 10; i++) {
//            Roles adminRole = new Roles();
//            adminRole.setRole(Role.ADMIN);
//            Roles userRole = new Roles();
//            userRole.setRole(Role.USER);
//            user = new User();
//            user.setUsername("atul" + i);
//            user.setPassword(encoder.encode("atul" + i));
//            user.setIsActive(Boolean.TRUE);
//            user.setEmail("atul.zambre123@gmail.com");
//            user.setRoles(new HashSet<>() {{
//                add(adminRole);
//                add(userRole);
//            }});
////            userRepository.save(user);
//            userService.saveUser(user);
//            System.out.println("user added  username :" + user.getUsername());
//        }
//    }
//}
