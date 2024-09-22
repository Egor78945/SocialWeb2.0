package org.example.messages3service.service.s3.minio;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.example.messages3service.configuration.s3.minio.properties.MinIOProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinIOService {
    private final MinioClient minioClient;
    private final MinIOProperties minIOProperties;

    public void saveToMessageBucket(String messageAddress, InputStream message) throws Exception {
        minioClient
                .putObject(PutObjectArgs
                        .builder()
                        .stream(message, message.available(), -1)
                        .object(messageAddress)
                        .bucket(minIOProperties.getMESSAGE_BUCKET())
                        .build());
    }
}
