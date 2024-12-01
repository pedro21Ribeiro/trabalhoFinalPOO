package com.example.fatecpoo.DTO.SinopseDTO;

import com.example.fatecpoo.Entity.SinopseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Schema(description = "DTO que representa uma sinopse de um filme.")
public class SinopseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinopse")
    @Schema(description = "ID único da sinopse", example = "1")
    private Integer id;

    @Column(name="descricao_sinopse")
    @Schema(description = "Descrição detalhada da sinopse do filme", example = "Um filme sobre a coragem e a superação de um jovem em tempos difíceis.")
    private String descricao;

    public SinopseDTO(SinopseEntity sinopse){
        BeanUtils.copyProperties(sinopse, this);
    }

    public SinopseDTO(){}

    public SinopseDTO(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
