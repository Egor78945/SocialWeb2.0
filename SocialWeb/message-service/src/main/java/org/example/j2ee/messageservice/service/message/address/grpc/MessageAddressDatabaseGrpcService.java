package org.example.j2ee.messageservice.service.message.address.grpc;

import com.example.grpc.message_address.MessageAddressDatabaseService;
import com.example.grpc.message_address.MessageAddressServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageAddressDatabaseGrpcService {
    @GrpcClient("message-address-db-grpc-service")
    private MessageAddressServiceGrpc.MessageAddressServiceBlockingStub messageAddressServiceBlockingStub;

    public MessageAddressDatabaseService.ListMessageAddressResponse getMessageAddressesBySenderIdAndRecipientId(MessageAddressDatabaseService.LongLongRequest longLongRequest){
        return messageAddressServiceBlockingStub.getMessageAddressesBySenderIdAndRecipientId(longLongRequest);
    }
}
