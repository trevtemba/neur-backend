package com.neur.app.rest.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControllers {

    @PostMapping(value = "/auth/signup")
    public String createAccount() {
        return "Hello World";
    }

    @PostMapping(value = "/auth/login")
    public String loginAccount() {
        return "Hello World";
    }

    @PostMapping(value = "/auth/logout")
    public String logoutAccount() {
        return "Hello World";
    }

    @PostMapping(value = "/auth/forgot-password")
    public String forgotPassword() {
        return "Hello World";
    }

    @PostMapping(value = "/auth/reset-password")
    public String resetPassword() {
        return "Hello World";
    }

    @PostMapping(value = "/auth/reset-password")
    public String refreshToken() {
        return "Hello World";
    }

    @PostMapping(value = "/auth/verify-email")
    public String verifyEmail() {
        return "Hello World";
    }
}
