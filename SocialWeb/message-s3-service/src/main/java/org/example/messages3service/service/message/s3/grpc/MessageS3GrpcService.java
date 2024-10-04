package org.example.messages3service.service.message.s3.grpc;

import com.example.grpc.message.s3.MessageS3ServiceGrpc;
import com.example.grpc.message.s3.MessageS3ServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.messages3service.service.message.s3.minio.MinIOService;
import org.example.messages3service.util.message.s3.builder.MessageS3ServiceBuilder;
import org.example.messages3service.util.message.s3.converter.MessageS3ServiceConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class MessageS3GrpcService extends MessageS3ServiceGrpc.MessageS3ServiceImplBase {
    private final MinIOService minIOService;

    @Override
    public void getMessages(MessageS3ServiceOuterClass.ListStringRequest request, StreamObserver<MessageS3ServiceOuterClass.ListStringRequest> responseObserver) {
        List<String> messageList = request
                .getStringListList()
                .stream()
                .map(MessageS3ServiceConverter::convert)
                .peek(s -> {
                    try {
                        minIOService.getFromMessageBucket(s);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        MessageS3ServiceOuterClass.ListStringRequest listStringRequestList = MessageS3ServiceBuilder.build(messageList);
        responseObserver.onNext(listStringRequestList);
        responseObserver.onCompleted();
    }
}
