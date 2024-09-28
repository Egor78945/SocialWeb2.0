package org.example.messageaddressdbservice.messageaddressdbservice.service.message_address;

import lombok.RequiredArgsConstructor;
import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;
import org.example.messageaddressdbservice.messageaddressdbservice.repository.MessageAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageAddressService {
    private final MessageAddressRepository messageAddressRepository;

    public void saveMessageAddress(MessageAddress messageAddress){
        messageAddressRepository.save(messageAddress);
    }

    @Transactional
    public List<MessageAddress> getAllBySenderIdByRecipientId(Long senderId, Long recipientId){
        return messageAddressRepository.findAllBySenderIdAndRecipientId(senderId, recipientId);
    }
}
