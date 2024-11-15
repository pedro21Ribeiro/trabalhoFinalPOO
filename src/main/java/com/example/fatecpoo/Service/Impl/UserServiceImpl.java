package com.example.fatecpoo.Service.Impl;

import com.example.fatecpoo.DTO.UserDTO.UserDTO;
import com.example.fatecpoo.DTO.UserDTO.UserRegisterRequestDTO;
import com.example.fatecpoo.Entity.UserEntity;
import com.example.fatecpoo.Infra.Security.TokenService;
import com.example.fatecpoo.Repository.UserRepository;
import com.example.fatecpoo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }
    @Override
    public UserDTO registerUser(UserRegisterRequestDTO request) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByEmail(request.getEmail());

        // Caso o usuário ainda não exista
        if (userEntityOptional.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setSenha(passwordEncoder.encode(request.getSenha()));
            newUser.setEmail(request.getEmail());
            newUser.setNome(request.getNome());
            newUser.setRole(request.getRole());
            System.out.println(newUser);
            // Salvar o novo usuário
            this.userRepository.save(newUser);

            // Gerar token para o novo usuário
            String token = this.tokenService.generateToken(newUser);
            return new UserDTO(newUser.getId(), newUser.getNome(), newUser.getEmail(), newUser.getRole());
        }

        // Caso o usuário já exista
        UserEntity existingUser = userEntityOptional.get();
        return new UserDTO(existingUser.getId(), existingUser.getNome(), existingUser.getEmail(), existingUser.getRole());
    }



}
