package org.example.messageaddressdbservice.messageaddressdbservice.utils.message_address.converter.grpc;

import com.example.grpc.message_address.MessageAddressDatabaseService;
import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;
import org.example.messageaddressdbservice.messageaddressdbservice.utils.message_address.builder.grpc.MessageAddressDatabaseGrpcServiceBuilder;

import java.util.List;

public class MessageAddressDatabaseGrpcServiceConverter {
    public static MessageAddressDatabaseService.ListMessageAddressResponse convertTo(List<MessageAddress> messageAddressList) {
        List<MessageAddressDatabaseService.MessageAddressResponse> messageAddressResponses = messageAddressList
                .stream()
                .map(MessageAddressDatabaseGrpcServiceBuilder::build)
                .toList();

        return MessageAddressDatabaseService.ListMessageAddressResponse
                .newBuilder()
                .addAllMessageAddressResponses(messageAddressResponses)
                .build();
    }
}
