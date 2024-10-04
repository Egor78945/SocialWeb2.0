package org.example.messages3service.service.message.s3.minio;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.example.messages3service.configuration.s3.minio.properties.MinIOProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    public String getFromMessageBucket(String address) throws Exception {
        return new String(minioClient
                .getObject(GetObjectArgs
                        .builder()
                        .bucket(minIOProperties.getMESSAGE_BUCKET())
                        .object(address)
                        .build())
                .readAllBytes());
    }
}
