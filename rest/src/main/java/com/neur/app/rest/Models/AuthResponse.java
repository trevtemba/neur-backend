package com.neur.app.rest.Models;

public class AuthResponse {
    private String accessToken;
    private String status;

    // Constructor
    public AuthResponse(String accessToken, String status) {
        this.accessToken = accessToken;
        this.status = status;
    }

    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
