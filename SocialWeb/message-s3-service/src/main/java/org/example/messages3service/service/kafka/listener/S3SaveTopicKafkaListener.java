package org.example.messages3service.service.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.example.messages3service.model.dto.response.MessageDataModel;
import org.example.messages3service.service.message.s3.minio.MinIOService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class S3SaveTopicKafkaListener {
    private final MinIOService minIOService;

    @KafkaListener(topics = "${kafka.topic.s3.save}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "messageDataListenerContainerFactory")
    public void messageDataTopicListener(MessageDataModel messageDataModel) throws Exception {
        minIOService.saveToMessageBucket(messageDataModel.getMessageAddress(), new ByteArrayInputStream(messageDataModel.getMessage().getBytes()));
    }
}
