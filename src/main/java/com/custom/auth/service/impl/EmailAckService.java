package com.custom.auth.service.impl;

import com.custom.auth.entity.User;
import com.custom.auth.model.EmailAckModel;
import com.custom.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmailAckService {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "email-send-ack-topic", groupId = "emailAckGroup", containerFactory = "emailAckKafkaListenerContainerFactory")
    public void checkEmailAck(EmailAckModel emailAckModel){
        if(emailAckModel.getIsEmailSent()){
            User user = userRepository.findByEmail(emailAckModel.getEmailTo()).orElseThrow(()->new UsernameNotFoundException("User not found"));
            user.setIsActivationLinkSent(Boolean.TRUE);
            userRepository.save(user);
        }
    }
}
