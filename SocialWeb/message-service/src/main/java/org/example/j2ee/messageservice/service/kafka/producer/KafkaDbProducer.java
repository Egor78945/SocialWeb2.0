package org.example.j2ee.messageservice.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.configuration.kafka.KafkaDetails;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.service.kafka.KafkaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDbProducer {
    private final KafkaDetails kafkaDetails;
    private final KafkaService kafkaService;

    public void send(String key, MessageAddressModel messageAddressModel) {
        kafkaService.send(kafkaDetails.getKAFKA_DB_SAVE_REQUEST_TOPIC(), key, messageAddressModel);
    }
}
