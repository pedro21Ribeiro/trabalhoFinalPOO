package com.example.fatecpoo.Controller;

import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.DTO.ReviewDTO.ReviewDTO;
import com.example.fatecpoo.Infra.Security.SecurityConfig;
import com.example.fatecpoo.Service.Impl.FilmeServiceImpl;
import com.example.fatecpoo.Service.Impl.ReviewServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/review")
@Tag(name = "Review", description = "Controller para a inserção, alteração, consulta e deleção de reviews.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ReviewController {
    private ReviewServiceImpl reviewServiceImpl;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @GetMapping("/todos")
    @Operation(summary = "Lista todas as reviews", description = "Retorna uma lista de todas as reviews cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<ReviewDTO> ListarTodos (){
        return reviewServiceImpl.findAllReviews();
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona um novo filme", description = "Insere um novo filme no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })

    public void inserirReview(@RequestBody ReviewDTO reviewDTO){
        reviewServiceImpl.saveReview(reviewDTO);
    }


    @PutMapping("/alterar")
    @Operation(summary = "Altera um filme", description = "Atualiza os dados de um filme existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ReviewDTO alterarReview(@RequestBody ReviewDTO reviewDTO){
        return reviewServiceImpl.updateReview(reviewDTO);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Exclui um filme", description = "Remove um filme do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Review não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirReview(@PathVariable("id") Integer id){
        reviewServiceImpl.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filme/{id}")
    @Operation(summary = "Lista filmes pelo diretor", description = "Retorna uma lista de filmes dirigidos por um diretor específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<ReviewDTO> listarReviewsPeloFilme(@PathVariable("id") Integer id){
        return reviewServiceImpl.findByFilme(id);
    }
}








