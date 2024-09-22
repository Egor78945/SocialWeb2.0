package org.example.j2ee.messageservice.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.configuration.kafka.properties.KafkaProperties;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.service.kafka.KafkaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDbProducer {
    private final KafkaProperties kafkaProperties;
    private final KafkaService kafkaService;

    public void send(String key, MessageAddressModel messageAddressModel) {
        kafkaService.send(kafkaProperties.getKAFKA_DB_SAVE_TOPIC(), key, messageAddressModel);
    }
}
