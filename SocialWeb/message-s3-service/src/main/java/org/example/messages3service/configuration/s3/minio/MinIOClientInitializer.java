package org.example.messages3service.configuration.s3.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.messages3service.configuration.s3.minio.properties.MinIOProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class MinIOClientInitializer {
    private final MinioClient minioClient;
    private final MinIOProperties minIOProperties;

    @PostConstruct
    public void initBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIOProperties.getMESSAGE_BUCKET()).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIOProperties.getMESSAGE_BUCKET()).build());
        }
    }
}
