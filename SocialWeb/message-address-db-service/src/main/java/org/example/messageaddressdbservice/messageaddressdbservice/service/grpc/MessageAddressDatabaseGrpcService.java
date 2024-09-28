package org.example.messageaddressdbservice.messageaddressdbservice.service.grpc;

import com.example.grpc.message_address.MessageAddressDatabaseService;
import com.example.grpc.message_address.MessageAddressServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.messageaddressdbservice.messageaddressdbservice.service.MessageAddressService;
import org.example.messageaddressdbservice.messageaddressdbservice.utils.converter.MessageAddressDatabaseGrpcServiceConverter;

@GrpcService
@RequiredArgsConstructor
public class MessageAddressDatabaseGrpcService extends MessageAddressServiceGrpc.MessageAddressServiceImplBase {
    private final MessageAddressService messageAddressService;

    @Override
    public void getMessageAddressesBySenderIdAndRecipientId(MessageAddressDatabaseService.LongLongRequest request, StreamObserver<MessageAddressDatabaseService.ListMessageAddressResponse> responseObserver) {
        responseObserver.onNext(MessageAddressDatabaseGrpcServiceConverter.convertTo(messageAddressService.getAllBySenderIdByRecipientId(request.getFirstLong(), request.getSecondLong())));
        responseObserver.onCompleted();
    }
}
