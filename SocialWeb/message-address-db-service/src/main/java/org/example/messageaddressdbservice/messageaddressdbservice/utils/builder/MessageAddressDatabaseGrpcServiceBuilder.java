package org.example.messageaddressdbservice.messageaddressdbservice.utils.builder;

import com.example.grpc.message_address.MessageAddressDatabaseService;
import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;

public class MessageAddressDatabaseGrpcServiceBuilder {
    public static MessageAddressDatabaseService.MessageAddressResponse build(MessageAddress messageAddress) {
        return MessageAddressDatabaseService.MessageAddressResponse
                .newBuilder()
                .setSenderId(messageAddress.getSenderId())
                .setRecipientId(messageAddress.getRecipientId())
                .setTimestamp(messageAddress.getSendDate().getTime())
                .build();
    }
}
