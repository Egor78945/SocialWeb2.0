package org.example.j2ee.messageservice.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.configuration.kafka.properties.KafkaProperties;
import org.example.j2ee.messageservice.model.dto.kafka.MessageDataModel;
import org.example.j2ee.messageservice.service.kafka.KafkaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaS3Producer {
    private final KafkaService kafkaService;
    private final KafkaProperties kafkaProperties;

    public void send(String key, MessageDataModel messageDataModel) {
        kafkaService.send(kafkaProperties.getKAFKA_S3_SAVE_TOPIC(), key, messageDataModel);
    }
}
