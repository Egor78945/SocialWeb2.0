package org.example.messages3service.service.message.s3.grpc;

import com.example.grpc.message.s3.MessageS3ServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageS3GrpcService extends MessageS3ServiceGrpc.MessageS3ServiceImplBase {

}
