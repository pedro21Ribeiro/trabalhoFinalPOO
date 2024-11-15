package com.example.fatecpoo.DTO.SinopseDTO;

import com.example.fatecpoo.Entity.SinopseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class SinopseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinopse")
    private Integer id;

    @Column(name="descricao_sinopse")
    private String descricao;

    public SinopseDTO(SinopseEntity sinopse){
        BeanUtils.copyProperties(sinopse, this);
    }

    public SinopseDTO(){}

}
