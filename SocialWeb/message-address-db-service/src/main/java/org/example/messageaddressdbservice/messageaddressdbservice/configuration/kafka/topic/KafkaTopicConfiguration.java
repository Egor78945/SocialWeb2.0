package org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.topic;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.properties.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public NewTopic dbSaveTopic(){
        return TopicBuilder
                .name(kafkaProperties.getKAFKA_DB_SAVE_TOPIC())
                .replicas(2)
                .partitions(3)
                .build();
    }
}
