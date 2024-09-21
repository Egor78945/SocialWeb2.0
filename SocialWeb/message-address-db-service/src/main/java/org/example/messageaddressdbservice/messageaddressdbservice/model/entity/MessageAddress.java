package org.example.messageaddressdbservice.messageaddressdbservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.messageaddressdbservice.messageaddressdbservice.model.dto.kafka.response.MessageAddressModel;

import java.sql.Date;

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
    private Date sendDate;

    public MessageAddress() {

    }

    public MessageAddress(MessageAddressModel messageAddressModel) {
        this.senderId = messageAddressModel.getSenderId();
        this.recipientId = messageAddressModel.getRecipientId();
        this.sendDate = new Date(messageAddressModel.getTimestamp());
    }
}
