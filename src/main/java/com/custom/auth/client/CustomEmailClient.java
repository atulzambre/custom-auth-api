package com.custom.auth.client;

import com.custom.auth.exception.CustomEmailException;
import com.custom.auth.model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomEmailClient {

    @Autowired
    private RestTemplate restTemplate;

    public void sendActivationLink(EmailModel emailModel) {
        try {
            ResponseEntity res = restTemplate.postForEntity("http://localhost:9991/email/send", emailModel, ResponseEntity.class);
        } catch (ResourceAccessException e) {
            throw new CustomEmailException(e.getMessage());
        }

    }
}
