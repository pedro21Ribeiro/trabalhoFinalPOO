package com.example.fatecpoo.DTO.FilmeDTO;

import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Entity.SinopseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter

public class FilmeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Integer id;

    @Column(nullable = false)
    private String nomeFilme;


    private Double scoreFilme;


    private String logoFilme;

    @OneToOne
    @JoinColumn(name = "fk_sinopse", referencedColumnName = "id_sinopse")
    private SinopseEntity sinopse;

    @ManyToOne
    @JoinColumn(name = "fk_diretor", referencedColumnName = "id_diretor")
    private DiretorEntity diretor;

    public FilmeDTO(FilmeEntity filme){
        BeanUtils.copyProperties(filme, this);
    }

    public FilmeDTO(){}



}

