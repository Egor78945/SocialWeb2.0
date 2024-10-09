package org.example.j2ee.messageservice.service.kafka;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.model.dto.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.model.dto.kafka.MessageDataModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String, MessageAddressModel> addressKafkaTemplate;
    private final KafkaTemplate<String, MessageDataModel> dataKafkaTemplate;

    @Transactional("kafkaDbTransactionManager")
    public void send(String topic, String key, MessageAddressModel value) {
        addressKafkaTemplate.send(topic, key, value);
    }

    @Transactional("kafkaS3TransactionManager")
    public void send(String topic, String key, MessageDataModel value) {
        dataKafkaTemplate.send(topic, key, value);
    }
}
