package com.soside.backend.exceptions;

public class BirthRecordNotFoundException extends RuntimeException {
    public BirthRecordNotFoundException(Long id) {
        super("BirthRecord not found with id " + id);
    }
}