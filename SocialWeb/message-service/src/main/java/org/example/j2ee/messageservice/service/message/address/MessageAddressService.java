package org.example.j2ee.messageservice.service.message.address;

import com.example.grpc.message_address.MessageAddressDatabaseService;
import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.service.kafka.producer.KafkaDbProducer;
import org.example.j2ee.messageservice.service.message.address.grpc.MessageAddressDatabaseGrpcService;
import org.example.j2ee.messageservice.util.message.address.builder.grpc.MessageAddressDatabaseGrpcServiceBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageAddressService {
    private final MessageAddressDatabaseGrpcService messageAddressDatabaseGrpcService;
    private final KafkaDbProducer kafkaDbProducer;

    public void sendMessageAddress(Long senderId, Long recipientId, Long currentTime){
        kafkaDbProducer.send(String.valueOf(senderId + recipientId + currentTime), new MessageAddressModel(senderId, recipientId, currentTime));
    }

    public MessageAddressDatabaseService.ListMessageAddressResponse getMessageAddressesBySenderIdAndRecipientId(Long senderId, Long recipientId){
        return messageAddressDatabaseGrpcService.getMessageAddressesBySenderIdAndRecipientId(MessageAddressDatabaseGrpcServiceBuilder.build(senderId, recipientId));
    }
}
