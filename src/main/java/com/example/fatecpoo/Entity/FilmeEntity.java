package com.example.fatecpoo.Entity;


import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="tb_filme")
@Getter
@Setter
public class FilmeEntity {

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

    public FilmeEntity(FilmeDTO filmeDTO){
        BeanUtils.copyProperties(filmeDTO, this);
    }

    public FilmeEntity(){}



}
