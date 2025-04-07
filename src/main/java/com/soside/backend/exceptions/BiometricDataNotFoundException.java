package com.soside.backend.exceptions;

public class BiometricDataNotFoundException extends RuntimeException {

    public BiometricDataNotFoundException() {
        super("Biometric data not found.");
    }

    public BiometricDataNotFoundException(String message) {
        super(message);
    }
}
