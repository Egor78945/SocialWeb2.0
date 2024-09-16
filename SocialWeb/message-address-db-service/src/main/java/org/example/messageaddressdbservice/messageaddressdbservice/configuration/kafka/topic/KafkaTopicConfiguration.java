package org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.topic;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.KafkaDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {
    private final KafkaDetails kafkaDetails;

    @Bean
    public NewTopic dbSaveRequestTopic(){
        return TopicBuilder
                .name(kafkaDetails.getKAFKA_DB_SAVE_REQUEST_TOPIC())
                .replicas(2)
                .partitions(3)
                .build();
    }

    @Bean
    public NewTopic dbSaveResponseTopic(){
        return TopicBuilder
                .name(kafkaDetails.getKAFKA_DB_SAVE_RESPONSE_TOPIC())
                .replicas(2)
                .partitions(3)
                .build();
    }
}
