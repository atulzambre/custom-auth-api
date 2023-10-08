package com.custom.auth.service.impl;

import com.custom.auth.client.CustomEmailClient;
import com.custom.auth.entity.User;
import com.custom.auth.model.EmailModel;
import com.custom.auth.service.CustomEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomEmailServiceImpl implements CustomEmailService {

    @Autowired
    private CustomEmailClient customEmailClient;
    @Autowired
    private KafkaTemplate<String, EmailModel> kafkaTemplate;

    @Override
    public void sendActivationLink(User savedUser, String activationToken) {
        EmailModel emailModel = EmailModel.builder()
                .emailTo(savedUser.getEmail())
                .emailBody("Activation Link : http://localhost:9999/activateUser/" + activationToken)
                .emailSubject("Activate your account")
                .build();

        kafkaTemplate.send("email-send-topic", emailModel);
//        customEmailClient.sendActivationLink(emailModel);

    }
}
