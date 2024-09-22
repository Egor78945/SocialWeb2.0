package org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaProperties {
    private final String KAFKA_BOOTSTRAP_SERVER;
    private final String KAFKA_AUTO_OFFSET_RESET;
    private final String KAFKA_DB_SAVE_TOPIC;
    private final String KAFKA_DB_SAVE_TOPIC_GROUP_ID;

    public KafkaProperties(@Value("${spring.kafka.bootstrap-servers}") String KAFKA_BOOTSTRAP_SERVER, @Value("${spring.kafka.consumer.auto-offset-reset}") String KAFKA_AUTO_OFFSET_RESET, @Value("${kafka.topic.db.save}") String KAFKA_DB_SAVE_TOPIC, @Value("${spring.kafka.consumer.group-id}") String KAFKA_DB_SAVE_TOPIC_GROUP_ID) {
        this.KAFKA_BOOTSTRAP_SERVER = KAFKA_BOOTSTRAP_SERVER;
        this.KAFKA_AUTO_OFFSET_RESET = KAFKA_AUTO_OFFSET_RESET;
        this.KAFKA_DB_SAVE_TOPIC = KAFKA_DB_SAVE_TOPIC;
        this.KAFKA_DB_SAVE_TOPIC_GROUP_ID = KAFKA_DB_SAVE_TOPIC_GROUP_ID;
    }

    public String getKAFKA_BOOTSTRAP_SERVER() {
        return KAFKA_BOOTSTRAP_SERVER;
    }

    public String getKAFKA_AUTO_OFFSET_RESET() {
        return KAFKA_AUTO_OFFSET_RESET;
    }

    public String getKAFKA_DB_SAVE_TOPIC() {
        return KAFKA_DB_SAVE_TOPIC;
    }

    public String getKAFKA_DB_SAVE_TOPIC_GROUP_ID() {
        return KAFKA_DB_SAVE_TOPIC_GROUP_ID;
    }
}
