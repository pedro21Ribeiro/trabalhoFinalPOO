package com.example.fatecpoo.Entity;


import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="tb_sinopse")
@Getter
@Setter
@Schema(description = "Entidade que representa uma sinopse do sistema.")
public class SinopseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinopse")
    private Integer id;

    @Column(name="descricao_sinopse")
    private String descricao;

    public SinopseEntity(SinopseDTO sinopseDTO){
        BeanUtils.copyProperties(sinopseDTO, this);
    }

    public SinopseEntity(){}
}


