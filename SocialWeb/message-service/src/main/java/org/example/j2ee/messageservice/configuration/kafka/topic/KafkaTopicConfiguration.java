package org.example.j2ee.messageservice.configuration.kafka.topic;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.j2ee.messageservice.configuration.kafka.KafkaDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {
    private final KafkaDetails kafkaDetails;

    @Bean
    public NewTopic s3SaveTopic(){
        return TopicBuilder
                .name(kafkaDetails.getKAFKA_S3_SAVE_TOPIC())
                .replicas(2)
                .partitions(3)
                .build();
    }
}
