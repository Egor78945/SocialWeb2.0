package org.example.j2ee.messageservice.configuration.kafka.template;

import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.configuration.kafka.KafkaDetails;
import org.example.j2ee.messageservice.model.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.model.kafka.MessageDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaTemplateConfiguration {
    private final KafkaDetails kafkaDetails;

    @Bean
    public KafkaTemplate<String, MessageAddressModel> messageAddressModelKafkaTemplate(ProducerFactory<String, MessageAddressModel> producerFactory) {
        KafkaTemplate<String, MessageAddressModel> kafkaTemplate =  new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setTransactionIdPrefix(kafkaDetails.getKAFKA_DB_TOPIC_TRANSACTION_ID());

        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, MessageDataModel> messageDataModelKafkaTemplate(ProducerFactory<String, MessageDataModel> producerFactory) {
        KafkaTemplate<String, MessageDataModel> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setTransactionIdPrefix(kafkaDetails.getKAFKA_S3_TOPIC_TRANSACTION_ID());

        return kafkaTemplate;
    }
}
