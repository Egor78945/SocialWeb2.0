package org.example.messages3service.configuration.s3.minio.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MinIOProperties {
    private final String URL;
    private final String MESSAGE_BUCKET;
    private final String ACCESS_KEY;
    private final String SECRET_KEY;

    public MinIOProperties(@Value("${minio.url}") String URL, @Value("${minio.bucket}") String MESSAGE_BUCKET, @Value("${minio.security.access-key}") String ACCESS_KEY, @Value("${minio.security.secret-key}") String SECRET_KEY) {
        this.URL = URL;
        this.MESSAGE_BUCKET = MESSAGE_BUCKET;
        this.ACCESS_KEY = ACCESS_KEY;
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getURL() {
        return URL;
    }

    public String getMESSAGE_BUCKET() {
        return MESSAGE_BUCKET;
    }

    public String getACCESS_KEY() {
        return ACCESS_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }
}
