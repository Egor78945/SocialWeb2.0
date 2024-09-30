package org.example.messageaddressdbservice.messageaddressdbservice.repository;

import org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageAddressRepository extends JpaRepository<MessageAddress, Long> {
    @Query("SELECT new org.example.messageaddressdbservice.messageaddressdbservice.model.entity.MessageAddress(id, senderId, recipientId, sendDate) FROM MessageAddress where senderId=?1 and recipientId=?2 or senderId=?2 and recipientId=?1")
    List<MessageAddress> findAllBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
