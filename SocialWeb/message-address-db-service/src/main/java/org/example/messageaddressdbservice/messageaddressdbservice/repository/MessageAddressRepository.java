package org.example.messageaddressdbservice.messageaddressdbservice.repository;

import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageAddressRepository extends JpaRepository<MessageAddress, Long> {
}
