package org.example.j2ee.messageservice.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.enumeration.redis.RedisKey;
import org.example.j2ee.messageservice.model.kafka.MessageDataModel;
import org.example.j2ee.messageservice.model.user.UserProfile;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaS3Producer;
import org.example.j2ee.messageservice.service.message.address.MessageAddressService;
import org.example.j2ee.messageservice.service.message.s3.MessageS3Service;
import org.example.j2ee.messageservice.util.user.builder.grpc.UserDatabaseServiceBuilder;
import org.example.j2ee.messageservice.service.redis.RedisService;
import org.example.j2ee.messageservice.service.user.grpc.UserGrpcService;
import org.example.j2ee.messageservice.util.message.validator.MessageValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserGrpcService userGrpcService;
    private final RedisService redisService;
    private final MessageAddressService messageAddressService;
    private final MessageS3Service messageS3Service;

    public void sendMessage(Long recipientId, String message) throws JsonProcessingException {
        Long senderId = redisService.readValueAs(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class).getId();
        if (!MessageValidator.isValid(message)) {
            throw new IllegalArgumentException("Message is too long or empty. Maximum size of message - 500 symbols.");
        } else if (senderId.equals(recipientId)) {
            throw new IllegalArgumentException("You can't send messages to yourself.");
        } else if (!userGrpcService.existsUserById(UserDatabaseServiceBuilder.buildTo(senderId)).getBoolean()) {
            throw new IllegalArgumentException(String.format("User with id %s is not found.", senderId));
        } else if (!userGrpcService.existsUserById(UserDatabaseServiceBuilder.buildTo(recipientId)).getBoolean()) {
            throw new IllegalArgumentException(String.format("User with id %s is not found.", recipientId));
        }
        Long currentTime = System.currentTimeMillis() + 10800000;
        messageAddressService.sendMessageAddress(senderId, recipientId, currentTime);
        messageS3Service.sendMessage(senderId, recipientId, currentTime, message);
    }

    public List<String> getMessageAddresses(Long recipientId) throws JsonProcessingException {
        Long senderId = redisService.readValueAs(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class).getId();
        return messageAddressService.getMessageAddressesBySenderIdAndRecipientId(senderId, recipientId).getMessageAddressResponsesList()
                .stream()
                .map(r -> Long.toString(r.getTimestamp()))
                .toList();
    }
}
