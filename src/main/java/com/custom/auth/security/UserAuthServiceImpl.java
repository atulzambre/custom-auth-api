package com.custom.auth.security;

import com.custom.auth.entity.User;
import com.custom.auth.repository.UserRepository;
import com.custom.auth.util.EncDecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncDecUtil encDecUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User newUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<GrantedAuthority> authorities = newUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());

//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority(newUser.getRole().name()));
//        String decPassword = passwordEncoder.encode(encDecUtil.decryptString(newUser.getPassword()));
//        String decPassword = passwordEncoder.encode(encDecUtil.decryptString(newUser.getPassword()));
        return new org.springframework.security.core.userdetails.User(username, newUser.getPassword(), authorities);
    }
}
