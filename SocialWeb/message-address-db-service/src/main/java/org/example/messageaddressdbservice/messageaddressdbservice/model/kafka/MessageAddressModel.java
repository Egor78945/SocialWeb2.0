package org.example.messageaddressdbservice.messageaddressdbservice.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageAddressModel {
    private Long senderId;
    private Long recipientId;
    private Long timestamp;
}
