package org.example.messageaddressdbservice.messageaddressdbservice.service;

import lombok.RequiredArgsConstructor;
import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;
import org.example.messageaddressdbservice.messageaddressdbservice.repository.MessageAddressRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageAddressService {
    private final MessageAddressRepository messageAddressRepository;

    public void saveMessageAddress(MessageAddress messageAddress){
        messageAddressRepository.save(messageAddress);
    }
}
