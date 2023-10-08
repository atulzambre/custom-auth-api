package com.custom.auth.service.impl;

import com.custom.auth.entity.User;
import com.custom.auth.exception.BadRequestException;
import com.custom.auth.repository.UserRepository;
import com.custom.auth.service.CustomEmailService;
import com.custom.auth.service.UserService;
import com.custom.auth.util.EncDecUtil;
import com.custom.auth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    @Transactional
    public void saveUser(User user) {
        try {
            log.info("User registration started");
            user.setIsActive(Boolean.FALSE);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivateLinkAttempt(0L);
            user.setIsActivationLinkSent(Boolean.FALSE);
            User savedUser = userRepository.save(user);
            log.info("Activation Link Generation started");
            generateAndSendActivationLink(savedUser);
            log.info("Activation Link Generation completed");
            log.info("User registration completed");
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Violation of unique field" + e.getMessage());
        }
    }

    public void generateAndSendActivationLink(User savedUser) {
        String activationToken = jwtUtil.createActivationToken(savedUser);
        customEmailService.sendActivationLink(savedUser, activationToken);
//        savedUser.setActivateLinkAttempt(savedUser.getActivateLinkAttempt()+1);
//        userRepository.save(savedUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Object signIn(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        User loggedInUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        if (!loggedInUser.getIsActive()) {
            generateAndSendActivationLink(loggedInUser);
            return null;
        }
        return jwtUtil.createJwtToken(new HashMap<>(), loggedInUser);
    }

    @Override
    public void activateUser(String activationToken) {
        if (Objects.isNull(activationToken) || jwtUtil.isTokenExpired(activationToken)) {
            throw new BadRequestException("Invalid Activation Token");
        }
        String username = jwtUtil.extractUserName(activationToken);
        Claims claims = jwtUtil.extractAllClaims(activationToken);
        String usernameT = claims.get("username", String.class);
        String passwordT = claims.get("password", String.class);
        String emailT = claims.get("email", String.class);

        User user = userRepository.findByUsernameAndIsActive(username, Boolean.FALSE).orElseThrow(() -> new BadRequestException("Invalid Activation Token"));

        if (user.getUsername().equals(usernameT) && user.getPassword().equals(passwordT) && user.getEmail().equals(emailT)) {
            user.setIsActive(true);
            userRepository.save(user);
        } else
            throw new BadRequestException("Invalid Activation Token");
    }
}
