package com.soside.backend.payload;

import lombok.Getter;
import lombok.Setter;


public class ForgotPasswordRequest {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}