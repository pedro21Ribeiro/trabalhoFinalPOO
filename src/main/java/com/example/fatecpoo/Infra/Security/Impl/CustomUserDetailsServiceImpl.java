package com.example.fatecpoo.Infra.Security.Impl;

import com.example.fatecpoo.Entity.UserEntity;
import com.example.fatecpoo.Infra.Security.TokenService;
import com.example.fatecpoo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
                userEntity.getSenha(), new ArrayList<>());
    }
}
