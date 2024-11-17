package com.example.fatecpoo.Exceptions;

public class SuccessfulAuthentication extends RuntimeException {
  public SuccessfulAuthentication(String message) {
    super(message);
  }
}
