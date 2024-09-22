package org.example.messages3service.configuration.s3.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.messages3service.configuration.s3.minio.properties.MinIOProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@RequiredArgsConstructor
public class MinIOConfiguration {
    private final MinIOProperties minIOProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(minIOProperties.getURL())
                .credentials(minIOProperties.getACCESS_KEY(), minIOProperties.getSECRET_KEY())
                .build();
    }

    @PostConstruct
    public void initBucket(MinioClient minioClient) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIOProperties.getMESSAGE_BUCKET()).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIOProperties.getMESSAGE_BUCKET()).build());
        }
    }
}
