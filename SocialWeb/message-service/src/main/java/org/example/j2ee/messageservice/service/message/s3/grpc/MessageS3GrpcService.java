package org.example.j2ee.messageservice.service.message.s3.grpc;

import com.example.grpc.message.s3.MessageS3ServiceGrpc;
import com.example.grpc.message.s3.MessageS3ServiceOuterClass;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.j2ee.messageservice.util.message.s3.builder.grpc.MessageS3GrpcServiceBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageS3GrpcService {
    @GrpcClient("message-s3-grpc-service")
    private MessageS3ServiceGrpc.MessageS3ServiceBlockingStub messageS3ServiceBlockingStub;

    public MessageS3ServiceOuterClass.ListStringRequest getMessages(List<String> messageAddresses){
        return messageS3ServiceBlockingStub.getMessages(MessageS3GrpcServiceBuilder.build(messageAddresses));
    }
}
