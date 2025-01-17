package com.neur.app.rest.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Configuration
public class S3ClientConfiguration {

    @Bean
    public static S3AsyncClient s3AsyncClient() {
        try {
            return S3AsyncClient.crtBuilder()
                    .credentialsProvider(DefaultCredentialsProvider.create())
                    .region(Region.US_EAST_1)
                    .targetThroughputInGbps(20.0)
                    .minimumPartSizeInBytes(8 * 1025 * 1024L)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Could not create s3AsyncClient", e);
        }
    }
}

