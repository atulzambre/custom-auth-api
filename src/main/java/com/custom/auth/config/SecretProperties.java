package com.custom.auth.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Getter
public class SecretProperties {

    @Value("${aes.secret}")
    public String secret;

    @Value("${aes.salt}")
    public String salt;


}
