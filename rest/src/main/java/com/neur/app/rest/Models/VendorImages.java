package com.neur.app.rest.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="vendor_images")
public class VendorImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long businessUserId;
    @Column
    private String name;
    @Column
    private String s3Key;
    @Column
    private String s3Url;
    @Column
    private String imgType;
    @Column
    private int likeCount;
    @Column
    private String contentType;
    @Column
    private long size;
    @Column
    private LocalDateTime dateCreated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBusinessUserId() {
        return businessUserId;
    }

    public void setBusinessUserId(long businessUserId) {
        this.businessUserId = businessUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
