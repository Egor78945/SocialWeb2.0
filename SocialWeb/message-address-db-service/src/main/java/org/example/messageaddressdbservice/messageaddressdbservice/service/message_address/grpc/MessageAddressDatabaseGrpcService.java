package org.example.messageaddressdbservice.messageaddressdbservice.service.message_address.grpc;

import com.example.grpc.message_address.MessageAddressDatabaseService;
import com.example.grpc.message_address.MessageAddressServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.messageaddressdbservice.messageaddressdbservice.service.message_address.MessageAddressService;
import org.example.messageaddressdbservice.messageaddressdbservice.utils.message_address.converter.grpc.MessageAddressDatabaseGrpcServiceConverter;

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
