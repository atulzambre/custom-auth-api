package com.custom.auth.service.impl;

import com.custom.auth.client.CustomEmailClient;
import com.custom.auth.entity.User;
import com.custom.auth.model.EmailModel;
import com.custom.auth.service.CustomEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomEmailServiceImpl implements CustomEmailService {

    @Autowired
    private CustomEmailClient customEmailClient;

    @Override
    public void sendActivationLink(User savedUser) {
        EmailModel emailModel = EmailModel.builder()
                .emailTo(savedUser.getEmail())
                .emailBody("Activation Link ")
                .emailSubject("Activate your account")
                .build();
        customEmailClient.sendActivationLink(emailModel);

    }
}
