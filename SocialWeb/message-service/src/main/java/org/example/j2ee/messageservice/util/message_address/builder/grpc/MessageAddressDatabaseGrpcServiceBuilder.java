package org.example.j2ee.messageservice.util.message_address.builder.grpc;

import com.example.grpc.message_address.MessageAddressDatabaseService;

public class MessageAddressDatabaseGrpcServiceBuilder {
    public MessageAddressDatabaseService.LongLongRequest build(Long firstLong, Long secondLong) {
        return MessageAddressDatabaseService.LongLongRequest
                .newBuilder()
                .setFirstLong(firstLong)
                .setSecondLong(secondLong)
                .build();
    }
}
