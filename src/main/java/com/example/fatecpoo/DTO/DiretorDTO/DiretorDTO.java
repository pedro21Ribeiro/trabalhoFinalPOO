package com.example.fatecpoo.DTO.DiretorDTO;

import com.example.fatecpoo.Entity.DiretorEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class DiretorDTO {

    private Integer id;

    @Column(nullable = false)
    private String nomeDiretor;

    @Column(nullable = false)
    private String dataNascimentoDiretor;

    public DiretorDTO(DiretorEntity diretor){
        BeanUtils.copyProperties(diretor, this);
    }

    public DiretorDTO(){}



}
