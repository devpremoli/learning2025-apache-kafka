package com.example.riderequestservice.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:9092,broker-2:9093,broker-3:9094");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic rideRequestsTopic() {
        return new NewTopic("ride-requests", 3, (short) 2);
    }
}
