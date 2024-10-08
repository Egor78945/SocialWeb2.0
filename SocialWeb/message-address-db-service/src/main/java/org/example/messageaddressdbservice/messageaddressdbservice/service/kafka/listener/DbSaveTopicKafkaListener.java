package org.example.messageaddressdbservice.messageaddressdbservice.service.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.example.messageaddressdbservice.messageaddressdbservice.model.dto.kafka.response.MessageAddressModel;
import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;
import org.example.messageaddressdbservice.messageaddressdbservice.service.message_address.MessageAddressService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbSaveTopicKafkaListener {
    private final MessageAddressService messageAddressService;

    @KafkaListener(topics = "${kafka.topic.db.save}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "messageAddressListenerContainerFactory")
    public void messageAddressTopicListener(MessageAddressModel messageAddressModel) {
        messageAddressService.saveMessageAddress(new MessageAddress(messageAddressModel));
    }
}
