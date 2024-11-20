package com.example.fatecpoo.Entity;


import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.DTO.ReviewDTO.ReviewDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="tb_review")
@Getter
@Setter
@Schema(description = "Entidade que representa as reviews dos usuarios sobre um filme do sistema.")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Integer id;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "estrela", nullable = false)
    private Integer estrela;



    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "fk_filme", referencedColumnName = "id_filme")
    private FilmeEntity filme;

    public ReviewEntity(ReviewDTO reviewDTO){
        BeanUtils.copyProperties(reviewDTO, this);
    }

    public ReviewEntity(){}


}
