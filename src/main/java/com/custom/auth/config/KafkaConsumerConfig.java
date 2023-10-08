package com.custom.auth.config;

import com.custom.auth.model.EmailAckModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, EmailAckModel> emailAckConsumerFactory() {
        Map<String, Object> consumerProperties = new HashMap<>();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.0.0.94:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "emailackgroup");
        return new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(), new JsonDeserializer<>(EmailAckModel.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailAckModel> emailAckKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailAckModel> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(emailAckConsumerFactory());
        return containerFactory;
    }
}
