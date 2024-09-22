package org.example.messages3service.configuration.kafka.factory.listener_container;

import org.example.messages3service.model.dto.response.MessageDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaListenerContainerFactoryConfiguration {
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageDataModel>> messageDataListenerContainerFactory(ConsumerFactory<String, MessageDataModel> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MessageDataModel>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
