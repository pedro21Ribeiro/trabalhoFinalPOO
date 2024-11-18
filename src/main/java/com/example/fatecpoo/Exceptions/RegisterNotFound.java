package com.example.fatecpoo.Exceptions;

public class RegisterNotFound extends RuntimeException {
  public RegisterNotFound(String message) {
    super(message);
  }
}
