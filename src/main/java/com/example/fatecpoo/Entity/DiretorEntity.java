package com.example.fatecpoo.Entity;


import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="tb_Diretor")
@Getter
@Setter
public class DiretorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diretor")
    private Integer id;

    @Column(nullable = false)
    private String nomeDiretor;

    @Column(nullable = false)
    private String dataNascimentoDiretor;


    public DiretorEntity(DiretorDTO diretorDTO){
        BeanUtils.copyProperties(diretorDTO, this);
    }
    public DiretorEntity(){

    }



}
