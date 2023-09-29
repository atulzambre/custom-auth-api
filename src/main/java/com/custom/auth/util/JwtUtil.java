package com.custom.auth.util;

import com.custom.auth.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public String createJwtToken(User user){

        Map<String, Object> userMap= new HashMap<>();
        userMap.put("activate-user",user);
        return Jwts.builder()
                .setIssuer("custom-auth-api")
                .setSubject("activation-token")
//                .claim("activate-user")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date(10000))
                .compact();
    }

}
