package com.example.fatecpoo.Controller;


import com.example.fatecpoo.DTO.LoginDTO.LoginDTO;
import com.example.fatecpoo.DTO.UserDTO.UserRegisterRequestDTO;
import com.example.fatecpoo.Entity.UserEntity;
import com.example.fatecpoo.Exceptions.EmptyFieldException;
import com.example.fatecpoo.Exceptions.IncorrectPassoword;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
import com.example.fatecpoo.Exceptions.SuccessfulAuthentication;
import com.example.fatecpoo.Infra.Security.TokenService;
import com.example.fatecpoo.Repository.UserRepository;
import com.example.fatecpoo.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private final UserServiceImpl userService;

    @Autowired

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          TokenService tokenService,
                          UserServiceImpl userService
                          ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO body) {

        if (body.email() == null || body.email().isEmpty() || body.senha() == null || body.senha().isEmpty()) {
            throw new EmptyFieldException("Email e senha são obrigatórios");
        }

        UserEntity userEntity = this.userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RegisterNotFound("Usuario não encontrado"));

        if (passwordEncoder.matches(body.senha(), userEntity.getSenha())) {
            String token = this.tokenService.generateToken(userEntity);
            throw new SuccessfulAuthentication("Autenticação bem-sucedida. Token: " + token);
        }
        throw new IncorrectPassoword();
        //return ResponseEntity.badRequest().body("Senha incorreta");
    }

    @PostMapping("/register")
    public void Register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO){
        userService.registerUser(userRegisterRequestDTO);
    }

}
