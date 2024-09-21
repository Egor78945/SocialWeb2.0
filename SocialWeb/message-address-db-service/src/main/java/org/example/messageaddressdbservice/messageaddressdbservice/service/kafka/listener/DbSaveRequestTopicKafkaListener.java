package org.example.messageaddressdbservice.messageaddressdbservice.service.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.KafkaDetails;
import org.example.messageaddressdbservice.messageaddressdbservice.model.kafka.MessageAddressModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbSaveRequestTopicKafkaListener {
    private final KafkaDetails kafkaDetails;

    @KafkaListener(topics = "${kafka.topic.db.save-request}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "messageAddressListenerContainerFactory")
    public void messageAddressTopicListener(MessageAddressModel messageAddressModel) {
        System.out.println(messageAddressModel);
    }
}
