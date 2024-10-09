package org.example.j2ee.messageservice.util.message.address.builder.grpc;

import com.example.grpc.message.address.MessageAddressDatabaseService;

public class MessageAddressDatabaseGrpcServiceBuilder {
    public static MessageAddressDatabaseService.LongLongRequest build(Long firstLong, Long secondLong) {
        return MessageAddressDatabaseService.LongLongRequest
                .newBuilder()
                .setFirstLong(firstLong)
                .setSecondLong(secondLong)
                .build();
    }
}
