package com.example.fatecpoo.Infra.Security;

import com.example.fatecpoo.Entity.UserEntity;

import java.time.Instant;

public interface TokenService {

    String generateToken(UserEntity userEntity);

    String validateToken(String token);

    Instant generateExpirationDate();
}
