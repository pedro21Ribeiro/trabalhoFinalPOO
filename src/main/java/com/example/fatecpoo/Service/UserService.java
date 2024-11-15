package com.example.fatecpoo.Service;

import com.example.fatecpoo.DTO.UserDTO.UserDTO;
import com.example.fatecpoo.DTO.UserDTO.UserRegisterRequestDTO;

public interface UserService {

    UserDTO registerUser(UserRegisterRequestDTO request);


}
