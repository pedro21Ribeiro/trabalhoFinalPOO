package com.example.fatecpoo.DTO.UserDTO;

import com.example.fatecpoo.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequestDTO {


    private String nome;
    private String email;
    private String senha;
    private String role;

    public UserRegisterRequestDTO(UserEntity userEntity){
        BeanUtils.copyProperties(userEntity, this);
    }

    public UserRegisterRequestDTO(){};



}
