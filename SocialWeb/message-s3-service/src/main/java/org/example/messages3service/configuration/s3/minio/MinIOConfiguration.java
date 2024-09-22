package org.example.messages3service.configuration.s3.minio;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.example.messages3service.configuration.s3.minio.properties.MinIOProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
