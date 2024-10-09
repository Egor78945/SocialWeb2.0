package org.example.j2ee.messageservice.configuration.kafka.template;

import org.example.j2ee.messageservice.model.dto.kafka.MessageAddressModel;
import org.example.j2ee.messageservice.model.dto.kafka.MessageDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class KafkaTransactionManagerConfiguration {
    @Bean
    public PlatformTransactionManager kafkaDbTransactionManager(ProducerFactory<String, MessageAddressModel> producerFactory){
        return new KafkaTransactionManager<>(producerFactory);
    }

    @Bean
    public PlatformTransactionManager kafkaS3TransactionManager(ProducerFactory<String, MessageDataModel> producerFactory){
        return new KafkaTransactionManager<>(producerFactory);
    }
}
