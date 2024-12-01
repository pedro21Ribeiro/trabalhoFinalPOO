package com.example.fatecpoo.DTO.ReviewDTO;

import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.Entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
@Getter
@Setter
@Schema(description = "DTO para representar os dados de uma review.")
public class ReviewDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    @Schema(description = "ID único do review", example = "10")
    private Integer id;

    @Column(name = "comentario", nullable = false)
    @Schema(description = "Conteúdo da review", example = "Achei o filme legal, e o final foi bem empolgante, mas teve alguns erros de roteiro que deixaram a desejar, em resumo um bom filme, mas não espere algo como digno do oscar")
    private String comentario;

    @Column(name = "estrela", nullable = false)
    @Schema(description = "Quantidade de estrelas que o filme merece de 1 a 5", example = "4")
    private Integer estrela;



    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "fk_filme", referencedColumnName = "id_filme")
    private FilmeEntity filme;

    public ReviewDTO(ReviewEntity reviewEntity){
        BeanUtils.copyProperties(reviewEntity, this);
    }

    public ReviewDTO(){}

}
