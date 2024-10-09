package org.example.j2ee.messageservice.service.message;

import com.example.grpc.message.address.MessageAddressDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.enumeration.redis.RedisKey;
import org.example.j2ee.messageservice.model.dto.message.MessageList;
import org.example.j2ee.messageservice.model.dto.user.UserProfile;
import org.example.j2ee.messageservice.service.message.address.MessageAddressService;
import org.example.j2ee.messageservice.service.message.s3.MessageS3Service;
import org.example.j2ee.messageservice.service.redis.RedisService;
import org.example.j2ee.messageservice.service.user.grpc.UserGrpcService;
import org.example.j2ee.messageservice.util.message.address.converter.grpc.MessageAddressDatabaseGrpcServiceConverter;
import org.example.j2ee.messageservice.util.message.validator.MessageValidator;
import org.example.j2ee.messageservice.util.user.builder.grpc.UserDatabaseServiceBuilder;
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
        Long currentTimeGMTP3 = System.currentTimeMillis() + 10800000;
        messageAddressService.sendMessageAddress(senderId, recipientId, currentTimeGMTP3);
        messageS3Service.sendMessage(senderId, recipientId, currentTimeGMTP3, message);
    }

    public MessageList getMessageAddresses(Long recipientId) throws JsonProcessingException {
        Long senderId = redisService.readValueAs(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class).getId();
        List<MessageAddressDatabaseService.MessageAddressResponse> messageAddresses = messageAddressService.getMessageAddressesBySenderIdAndRecipientId(senderId, recipientId).getMessageAddressResponsesList();
        List<String> messages = messageS3Service.getMessages(MessageAddressDatabaseGrpcServiceConverter.convertToListMessageAddressString(messageAddresses));
        return new MessageList(MessageAddressDatabaseGrpcServiceConverter.convertToListMessageAddressString(messageAddresses), messages, senderId.toString());
    }
}