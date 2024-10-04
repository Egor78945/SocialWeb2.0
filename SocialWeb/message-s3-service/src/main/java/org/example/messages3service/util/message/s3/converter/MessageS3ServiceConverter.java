package org.example.messages3service.util.message.s3.converter;

import com.example.grpc.message.s3.MessageS3ServiceOuterClass;

import java.util.List;

public class MessageS3ServiceConverter {
    public static String convert(MessageS3ServiceOuterClass.StringRequest stringRequest) {
        return stringRequest.getString();
    }

    public static List<String> convert(MessageS3ServiceOuterClass.ListStringRequest listStringRequest) {
        return listStringRequest
                .getStringListList()
                .stream()
                .map(s -> s.getString())
                .toList();
    }
}
