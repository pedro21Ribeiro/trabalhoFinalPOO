package com.example.fatecpoo.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer id;


    @Column(name="nome_usuario")
    private String nome;

    @Column(name="email_usuario")
    private String email;

    @Column(name="senha_usuario")
    private String senha;

    @Column(name="role_usuario")
    private String role;






}
