package org.example.messageaddressdbservice.messageaddressdbservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.messageaddressdbservice.messageaddressdbservice.model.dto.kafka.response.MessageAddressModel;

import java.sql.Timestamp;

@Entity
@Table(name = "message_address")
@Data
public class MessageAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "recipient_id")
    private Long recipientId;
    @Column(name = "send_date")
    private Timestamp sendDate;

    public MessageAddress() {

    }

    public MessageAddress(MessageAddressModel messageAddressModel) {
        this.senderId = messageAddressModel.getSenderId();
        this.recipientId = messageAddressModel.getRecipientId();
        this.sendDate = new Timestamp(messageAddressModel.getTimestamp());
    }

    public MessageAddress(Long id, Long senderId, Long recipientId, Timestamp sendDate) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.sendDate = sendDate;
    }
}
