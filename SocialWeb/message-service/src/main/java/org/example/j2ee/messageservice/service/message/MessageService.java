package org.example.j2ee.messageservice.service.message;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.service.builder.UserDatabaseServiceBuilder;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaDbProducer;
import org.example.j2ee.messageservice.service.user.grpc.UserGrpcService;
import org.example.j2ee.messageservice.service.validator.message.MessageValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final KafkaDbProducer kafkaDbProducer;
    private final UserGrpcService userGrpcService;

    public void sendMessage(Long senderId, Long recipientId, String message) {
        if (!MessageValidator.isValid(message)) {
            throw new IllegalArgumentException("Message is too long or empty. Maximum size of message - 500 symbols.");
        } else if (senderId.equals(recipientId)){
            throw new IllegalArgumentException("You can't send messages to yourself.");
        } else if (!userGrpcService.existsUserById(UserDatabaseServiceBuilder.buildTo(senderId)).getBoolean()){
            throw new IllegalArgumentException(String.format("User with id %s is not found.", senderId));
        } else if (!userGrpcService.existsUserById(UserDatabaseServiceBuilder.buildTo(recipientId)).getBoolean()){
            throw new IllegalArgumentException(String.format("User with id %s is not found.", recipientId));
        }
        Long currentTime = System.currentTimeMillis() + 10800000;
        kafkaDbProducer.send(String.valueOf(senderId + recipientId + currentTime), new MessageAddressModel(senderId, recipientId, currentTime));
    }
}
