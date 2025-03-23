package com.soside.backend.exceptions;

public class DeathRecordNotFoundException extends RuntimeException {
    public DeathRecordNotFoundException(Long id) {
        super("DeathRecord not found with id " + id);
    }
}