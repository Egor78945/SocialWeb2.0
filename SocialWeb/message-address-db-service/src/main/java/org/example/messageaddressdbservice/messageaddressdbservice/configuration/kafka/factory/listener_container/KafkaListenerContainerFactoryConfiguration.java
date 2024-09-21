package org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.factory.listener_container;

import org.example.messageaddressdbservice.messageaddressdbservice.model.dto.kafka.response.MessageAddressModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaListenerContainerFactoryConfiguration {
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageAddressModel>> messageAddressListenerContainerFactory(ConsumerFactory<String, MessageAddressModel> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MessageAddressModel>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
