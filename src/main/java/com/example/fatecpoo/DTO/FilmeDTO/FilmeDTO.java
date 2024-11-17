package com.example.fatecpoo.DTO.FilmeDTO;

import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Entity.SinopseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Schema(description = "DTO para representar os dados de um filme.")
public class FilmeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    @Schema(description = "ID único do filme", example = "10")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Nome do filme", example = "O Poderoso Chefão")
    private String nomeFilme;

    @Schema(description = "Pontuação do filme", example = "9.2")
    private Double scoreFilme;

    @Schema(description = "URL do logo do filme", example = "https://example.com/logo.png")
    private String logoFilme;

    @OneToOne
    @JoinColumn(name = "fk_sinopse", referencedColumnName = "id_sinopse")
    @Schema(description = "Sinopse associada ao filme")
    private SinopseEntity sinopse;

    @ManyToOne
    @JoinColumn(name = "fk_diretor", referencedColumnName = "id_diretor")
    @Schema(description = "Diretor associado ao filme")
    private DiretorEntity diretor;

    public FilmeDTO(FilmeEntity filme){
        BeanUtils.copyProperties(filme, this);
    }

    public FilmeDTO(){}



}

