package com.custom.auth.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SecretProperties {

    @Value("${aes.secret}")
    public String secret;

    @Value("${aes.salt}")
    public String salt;


}
