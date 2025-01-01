package com.neur.app.rest.Models;

import jakarta.persistence.*;

import org.springframework.context.annotation.Primary;

@Entity
@Table (name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long businessUserId;
    @Column
    private String name;
    @Column
    private float price;
    @Column
    private int duration;
    @Lob
    @Column
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public long getBusinessUser() {
        return businessUserId;
    }

    public void setBusinessUserId (long businessUserId) {
        this.businessUserId = businessUserId;
    }
}
