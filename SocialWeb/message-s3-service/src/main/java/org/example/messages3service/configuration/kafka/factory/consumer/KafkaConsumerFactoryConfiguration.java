package org.example.messages3service.configuration.kafka.factory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.messages3service.configuration.kafka.properties.KafkaProperties;
import org.example.messages3service.model.dto.response.MessageDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerFactoryConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, MessageDataModel> messageDataModelConsumerFactory(ObjectMapper objectMapper) {
        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, MessageDataModel>(kafkaConsumerProperties("org.example.j2ee.messageservice.model.kafka.MessageDataModel", "org.example.messages3service.model.dto.response.MessageDataModel"));
        kafkaConsumerFactory.setValueDeserializer(new JsonDeserializer<>(objectMapper));

        return kafkaConsumerFactory;
    }

    private Map<String, Object> kafkaConsumerProperties(String classPathFrom, String classPathTo){
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(JsonDeserializer.TYPE_MAPPINGS, String.format("%s:%s", classPathFrom, classPathTo));
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getKAFKA_BOOTSTRAP_SERVER());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getKAFKA_AUTO_OFFSET_RESET());

        return properties;
    }
}
