package org.example.j2ee.messageservice.service.message.s3;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.model.dto.kafka.MessageDataModel;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaS3Producer;
import org.example.j2ee.messageservice.service.message.s3.grpc.MessageS3GrpcService;
import org.example.j2ee.messageservice.util.message.s3.converter.grpc.MessageS3GrpcServiceConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageS3Service {
    private final KafkaS3Producer kafkaS3Producer;
    private final MessageS3GrpcService messageS3GrpcService;

    public void sendMessage(Long senderId, Long recipientId, Long currentTime, String message){
        String messageAddress = String.format("%s/%s/%s", senderId, recipientId, currentTime);
        kafkaS3Producer.send(String.valueOf(senderId + recipientId + currentTime), new MessageDataModel(messageAddress, message));
    }

    public List<String> getMessages(List<String> messageAddresses){
        System.out.println(messageAddresses);
        return MessageS3GrpcServiceConverter.convert(messageS3GrpcService.getMessages(messageAddresses));
    }
}
