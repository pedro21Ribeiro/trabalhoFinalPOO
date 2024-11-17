package com.example.fatecpoo.Entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidade que representa um usuário do sistema.")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    @Schema(description = "ID único do usuário", example = "1")
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
