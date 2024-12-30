package com.neur.app.rest.Models;

public class AuthResponse {
    private String accessToken;
    private String status;



    private String message;

    // Constructor
    public AuthResponse(String accessToken, String status, String message) {
        this.accessToken = accessToken;
        this.status = status;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
