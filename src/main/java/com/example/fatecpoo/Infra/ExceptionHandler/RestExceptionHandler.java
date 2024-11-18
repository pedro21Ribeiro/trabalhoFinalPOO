package com.example.fatecpoo.Infra.ExceptionHandler;

import com.example.fatecpoo.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExists.class)
    private ResponseEntity<String> emailAlreadyExists(EmailAlreadyExists exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
    }

    @ExceptionHandler(RegisterNotFound.class)
    private ResponseEntity<String> registerNotFound(RegisterNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(IncorrectPassoword.class)
    private ResponseEntity<String> incorrectPassowrd(IncorrectPassoword exception){
        return ResponseEntity.status(401).body("Senha incorreta");
    }

    @ExceptionHandler(EmptyFieldException.class)
    private ResponseEntity<String> emptyFieldException(EmptyFieldException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }


}
