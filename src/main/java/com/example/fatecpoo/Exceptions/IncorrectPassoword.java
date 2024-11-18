package com.example.fatecpoo.Exceptions;

public class IncorrectPassoword extends RuntimeException {
    public IncorrectPassoword(String message) {
        super(message);
    }
    public IncorrectPassoword() {
        super();
    }
}
