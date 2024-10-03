package org.example.messages3service.configuration.kafka.topic;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.messages3service.configuration.kafka.properties.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public NewTopic s3SaveTopic(){
        return TopicBuilder
                .name(kafkaProperties.getKAFKA_S3_SAVE_TOPIC())
                .replicas(3)
                .partitions(3)
                .build();
    }
}
