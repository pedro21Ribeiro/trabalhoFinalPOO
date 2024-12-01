package com.example.fatecpoo.Entity;


import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="tb_Diretor")
@Getter
@Setter
@Schema(description = "Entidade que representa um diretor do sistema.")
public class DiretorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diretor")
    private Integer id;

    @Column(name = "nome_diretor", nullable = false)
    private String nomeDiretor;

    @Column(nullable = false)
    private String dataNascimentoDiretor;


    public DiretorEntity(DiretorDTO diretorDTO){
        BeanUtils.copyProperties(diretorDTO, this);
    }
    public DiretorEntity(){

    }


    public DiretorEntity(Integer id, String nomeDiretor, String dataNascimentoDiretor) {
        this.id = id;
        this.nomeDiretor = nomeDiretor;
        this.dataNascimentoDiretor = dataNascimentoDiretor;
    }
}
