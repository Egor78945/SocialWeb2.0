package org.example.j2ee.messageservice.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.enumeration.redis.RedisKey;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.model.kafka.MessageDataModel;
import org.example.j2ee.messageservice.model.user.UserProfile;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaS3Producer;
import org.example.j2ee.messageservice.util.user.builder.UserDatabaseServiceBuilder;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaDbProducer;
import org.example.j2ee.messageservice.service.redis.RedisService;
import org.example.j2ee.messageservice.service.user.grpc.UserGrpcService;
import org.example.j2ee.messageservice.util.message.validator.message.MessageValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final KafkaDbProducer kafkaDbProducer;
    private final KafkaS3Producer kafkaS3Producer;
    private final UserGrpcService userGrpcService;
    private final RedisService redisService;

    public void sendMessage(Long recipientId, String message) throws JsonProcessingException {
        Long senderId = redisService.readValueAs(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class).getId();
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

        String messageAddress = String.format("%s/%s/%s", senderId, recipientId, currentTime);
        kafkaS3Producer.send(String.valueOf(senderId + recipientId + currentTime), new MessageDataModel(messageAddress, message));
    }
}
