package com.neur.app.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.concurrent.CompletableFuture;

@Service
public class S3Service {
    private final S3AsyncClient s3AsyncClient;

    @Autowired
    public S3Service(S3AsyncClient s3AsyncClient) {
        this.s3AsyncClient = s3AsyncClient;
    }

    public CompletableFuture<String> uploadFile(String bucketType, String key, MultipartFile file) {
        String bucketName = getBucketName(bucketType);
        try {
            return s3AsyncClient.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    AsyncRequestBody.fromBytes(file.getBytes())
            ).thenApply(putObjectResponse -> {
                return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.US_EAST_1.id(), key);
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to s3", e);
        }
    }

    public String getBucketName(String bucketType) {
        switch(bucketType) {
            case("profile"):
                return "neur-profile-pictures";
            case("background"):
                return "neur-background-pictures";
            case("client"):
                return "neur-client-pictures";
            default:
                throw new IllegalArgumentException("Invalid bucket type");
        }
    }
}
