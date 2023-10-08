//package com.custom.auth.security;
//
//import com.custom.auth.util.EncDecUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class Sha256PasswordEncoder implements PasswordEncoder {
//
//    @Autowired
//    private EncDecUtil encDecUtil;
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return encDecUtil.encryptString(rawPassword.toString());
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        String hashedPassword = encode(rawPassword);
//        return encodedPassword.equals(hashedPassword);
//    }
//}
