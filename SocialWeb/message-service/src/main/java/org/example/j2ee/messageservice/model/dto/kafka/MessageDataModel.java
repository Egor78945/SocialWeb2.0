package org.example.j2ee.messageservice.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDataModel {
    private String messageAddress;
    private String message;
}
