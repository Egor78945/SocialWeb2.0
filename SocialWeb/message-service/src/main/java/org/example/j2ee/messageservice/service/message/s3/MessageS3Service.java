package org.example.j2ee.messageservice.service.message.s3;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.model.kafka.MessageDataModel;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaS3Producer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageS3Service {
    private final KafkaS3Producer kafkaS3Producer;

    public void sendMessage(Long senderId, Long recipientId, Long currentTime, String message){
        String messageAddress = String.format("%s/%s/%s", senderId, recipientId, currentTime);
        kafkaS3Producer.send(String.valueOf(senderId + recipientId + currentTime), new MessageDataModel(messageAddress, message));
    }
}
