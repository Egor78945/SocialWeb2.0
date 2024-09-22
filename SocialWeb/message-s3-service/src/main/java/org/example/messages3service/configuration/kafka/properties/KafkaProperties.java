package org.example.messages3service.configuration.kafka.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaProperties {
    private final String KAFKA_BOOTSTRAP_SERVER;
    private final String KAFKA_AUTO_OFFSET_RESET;
    private final String KAFKA_S3_SAVE_TOPIC;
    private final String KAFKA_S3_TOPIC_TRANSACTION_ID;
    private final String KAFKA_S3_SAVE_TOPIC_GROUP_ID;

    public KafkaProperties(@Value("${spring.kafka.bootstrap-servers}") String KAFKA_BOOTSTRAP_SERVER, @Value("${spring.kafka.consumer.auto-offset-reset}") String KAFKA_AUTO_OFFSET_RESET, @Value("${kafka.topic.s3.save}") String KAFKA_S3_SAVE_TOPIC, @Value("${kafka.topic.s3.transaction-id}") String KAFKA_S3_TOPIC_TRANSACTION_ID, @Value("${spring.kafka.consumer.group-id}") String KAFKA_S3_SAVE_TOPIC_GROUP_ID) {
        this.KAFKA_BOOTSTRAP_SERVER = KAFKA_BOOTSTRAP_SERVER;
        this.KAFKA_AUTO_OFFSET_RESET = KAFKA_AUTO_OFFSET_RESET;
        this.KAFKA_S3_SAVE_TOPIC = KAFKA_S3_SAVE_TOPIC;
        this.KAFKA_S3_TOPIC_TRANSACTION_ID = KAFKA_S3_TOPIC_TRANSACTION_ID;
        this.KAFKA_S3_SAVE_TOPIC_GROUP_ID = KAFKA_S3_SAVE_TOPIC_GROUP_ID;
    }

    public String getKAFKA_BOOTSTRAP_SERVER() {
        return KAFKA_BOOTSTRAP_SERVER;
    }

    public String getKAFKA_AUTO_OFFSET_RESET() {
        return KAFKA_AUTO_OFFSET_RESET;
    }

    public String getKAFKA_S3_SAVE_TOPIC() {
        return KAFKA_S3_SAVE_TOPIC;
    }

    public String getKAFKA_S3_TOPIC_TRANSACTION_ID() {
        return KAFKA_S3_TOPIC_TRANSACTION_ID;
    }

    public String getKAFKA_S3_SAVE_TOPIC_GROUP_ID() {
        return KAFKA_S3_SAVE_TOPIC_GROUP_ID;
    }
}
