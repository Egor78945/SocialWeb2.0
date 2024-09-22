package org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.factory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.messageaddressdbservice.messageaddressdbservice.configuration.kafka.properties.KafkaProperties;
import org.example.messageaddressdbservice.messageaddressdbservice.model.dto.kafka.response.MessageAddressModel;
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
    public ObjectMapper objectMapper() {
        return new JsonMapper();
    }

    @Bean
    public ConsumerFactory<String, MessageAddressModel> messageAddressModelConsumerFactory(ObjectMapper objectMapper) {
        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, MessageAddressModel>(kafkaConsumerProperties("org.example.j2ee.messageservice.model.kafka.MessageAddressModel", "org.example.messageaddressdbservice.messageaddressdbservice.model.dto.kafka.response.MessageAddressModel"));
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
