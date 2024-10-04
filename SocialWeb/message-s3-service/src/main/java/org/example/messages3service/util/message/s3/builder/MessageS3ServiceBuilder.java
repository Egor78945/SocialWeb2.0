package org.example.messages3service.util.message.s3.builder;

import com.example.grpc.message.s3.MessageS3ServiceOuterClass;

import java.util.List;

public class MessageS3ServiceBuilder {
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
                .map(MessageS3ServiceBuilder::build)
                .toList();
        return MessageS3ServiceOuterClass.ListStringRequest
                .newBuilder()
                .addAllStringList(list)
                .build();
    }
}
