package org.example.messageaddressdbservice.messageaddressdbservice.utils.message_address.builder.grpc;

import com.example.grpc.message.address.MessageAddressDatabaseService;
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
