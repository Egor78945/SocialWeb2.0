package org.example.j2ee.messageservice.util.message.s3.converter.grpc;

import com.example.grpc.message.s3.MessageS3ServiceOuterClass;

import java.util.List;

public class MessageS3GrpcServiceConverter {
    public static List<String> convert(MessageS3ServiceOuterClass.ListStringRequest listStringRequest) {
        return listStringRequest
                .getStringListList()
                .stream()
                .map(r -> r.getString())
                .toList();
    }
}
