package com.example.fatecpoo.DTO.DiretorDTO;

import com.example.fatecpoo.Entity.DiretorEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Schema(description = "DTO para representar um diretor.")
public class DiretorDTO {

    @Schema(description = "ID único do diretor", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Nome completo do diretor", example = "Steven Spielberg")
    private String nomeDiretor;

    @Column(nullable = false)
    @Schema(description = "Data de nascimento do diretor no formato yyyy-MM-dd", example = "1946-12-18")
    private String dataNascimentoDiretor;

    public DiretorDTO(DiretorEntity diretor){
        BeanUtils.copyProperties(diretor, this);
    }

    public DiretorDTO(){}

    public DiretorDTO(Integer id, String nomeDiretor, String dataNascimentoDiretor) {
        this.id = id;
        this.nomeDiretor = nomeDiretor;
        this.dataNascimentoDiretor = dataNascimentoDiretor;
    }
}
