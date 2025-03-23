package com.soside.backend.services.email;

public interface EmailService {
    void sendPasswordResetEmail(String to, String resetLink);
}