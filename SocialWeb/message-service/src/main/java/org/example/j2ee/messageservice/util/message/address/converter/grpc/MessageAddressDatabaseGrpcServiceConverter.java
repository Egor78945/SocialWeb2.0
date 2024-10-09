package org.example.j2ee.messageservice.util.message.address.converter.grpc;

import com.example.grpc.message.address.MessageAddressDatabaseService;

import java.util.List;

public class MessageAddressDatabaseGrpcServiceConverter {
    public static String convertToMessageAddressString(MessageAddressDatabaseService.MessageAddressResponse messageAddressResponse){
        return String.format("%s/%s/%s", messageAddressResponse.getSenderId(), messageAddressResponse.getRecipientId(), messageAddressResponse.getTimestamp());
    }

    public static List<String> convertToListMessageAddressString(List<MessageAddressDatabaseService.MessageAddressResponse> messageAddressResponseList){
        return messageAddressResponseList.stream().map(MessageAddressDatabaseGrpcServiceConverter::convertToMessageAddressString).toList();
    }
}
