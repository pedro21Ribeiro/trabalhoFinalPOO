package com.example.fatecpoo.DTO.UserDTO;

public class UserDTO {

    private Integer id;
    private String nome;
    private String email;
    private String role;
    public UserDTO(Integer id, String nome, String email,String role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public UserDTO() {}


}
