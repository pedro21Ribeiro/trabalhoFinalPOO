package com.example.fatecpoo.Exceptions;

public class DuplicateRegistry extends RuntimeException {
    public DuplicateRegistry(String message) {
        super(message);
    }
}
