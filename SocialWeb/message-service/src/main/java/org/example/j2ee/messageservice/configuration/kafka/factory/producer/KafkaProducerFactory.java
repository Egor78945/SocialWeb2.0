package org.example.j2ee.messageservice.configuration.kafka.factory.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.j2ee.messageservice.configuration.kafka.KafkaDetails;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.model.kafka.MessageDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerFactory {
    private final KafkaDetails kafkaDetails;

    @Bean
    public ProducerFactory<String, MessageAddressModel> messageAddressModelProducerFactory(ObjectMapper objectMapper) {
        DefaultKafkaProducerFactory<String, MessageAddressModel> producerFactory = new DefaultKafkaProducerFactory<>(producerProperties(kafkaDetails.getKAFKA_DB_TOPIC_TRANSACTION_ID()));
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper));

        return producerFactory;
    }

    @Bean
    public ProducerFactory<String, MessageDataModel> messageDataModelProducerFactory(ObjectMapper objectMapper) {
        DefaultKafkaProducerFactory<String, MessageDataModel> producerFactory = new DefaultKafkaProducerFactory<>(producerProperties(kafkaDetails.getKAFKA_S3_TOPIC_TRANSACTION_ID()));
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper));

        return producerFactory;
    }

    private Map<String, Object> producerProperties(String transactionId) {
        Map<String, Object> producerProperties = new HashMap<>();

        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDetails.getKAFKA_BOOTSTRAP_SERVER());
        producerProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionId);
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);

        return producerProperties;
    }
}
