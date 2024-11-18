package com.example.fatecpoo.Controller;


import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import com.example.fatecpoo.Service.Impl.SinopseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sinopse")
@Tag(name = "Sinopse", description = "Controller para a pesquisa, inserção, alteração e deleção de sinopses.")
public class SinopseController {

    private SinopseServiceImpl sinopseServiceImpl;

    @Autowired
    public SinopseController(SinopseServiceImpl sinopseServiceImpl) {
        this.sinopseServiceImpl = sinopseServiceImpl;
    }

    @PostMapping("/pesquisar")
    @Operation(summary = "Pesquisa sinopses", description = "Retorna uma lista de sinopses que correspondem à descrição fornecida.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<SinopseDTO> ListarSinopses(@RequestBody SinopseDTO sinopseDTO){
        return sinopseServiceImpl.findSinopseByDescricao(sinopseDTO.getDescricao());
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Exclui uma sinopse", description = "Remove uma sinopse do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinopse excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sinopse não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirSinopse(@PathVariable("id") Integer id){
        sinopseServiceImpl.deleteSinopse(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona uma nova sinopse", description = "Insere uma nova sinopse no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sinopse adicionada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void InserirSinopse(@RequestBody SinopseDTO sinopseDTO){
        System.out.println(sinopseDTO.getDescricao());
        sinopseServiceImpl.saveSinopse(sinopseDTO);
    }

    @PutMapping("/alterar")
    @Operation(summary = "Altera uma sinopse", description = "Atualiza os dados de uma sinopse existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinopse atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Sinopse não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public SinopseDTO alterarSinopse(@RequestBody SinopseDTO sinopseDTO){
        return sinopseServiceImpl.updateSinopse(sinopseDTO);
    }


}
