package org.example.j2ee.messageservice.util.message.s3.builder.grpc;

import com.example.grpc.message.s3.MessageS3ServiceOuterClass;

import java.util.List;

public class MessageS3GrpcServiceBuilder {
    public static MessageS3ServiceOuterClass.StringRequest build(String string) {
        return MessageS3ServiceOuterClass
                .StringRequest
                .newBuilder()
                .setString(string)
                .build();
    }

    public static MessageS3ServiceOuterClass.ListStringRequest build(List<String> stringList) {
        List<MessageS3ServiceOuterClass.StringRequest> list = stringList
                .stream()
                .map(MessageS3GrpcServiceBuilder::build)
                .toList();
        return MessageS3ServiceOuterClass.ListStringRequest
                .newBuilder()
                .addAllStringList(list)
                .build();
    }
}
