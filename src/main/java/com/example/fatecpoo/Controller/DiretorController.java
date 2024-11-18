package com.example.fatecpoo.Controller;


import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.Infra.Security.SecurityConfig;
import com.example.fatecpoo.Repository.DiretorRepository;
import com.example.fatecpoo.Service.Impl.DiretorServiceImpl;
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
@RequestMapping(value = "/diretor")
@Tag(name="Diretor", description ="Controller para a inserção, alteração, consulta e deleção de diretores.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class DiretorController {


    private DiretorServiceImpl diretorServiceImpl;

    @Autowired
    public DiretorController(DiretorServiceImpl diretorServiceImpl) {
        this.diretorServiceImpl = diretorServiceImpl;
    }

    @GetMapping("/todos")
    @Operation(summary = "Lista todos os diretores", description = "Retorna uma lista de todos os diretores cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<DiretorDTO>  ListarTodos (){
        return diretorServiceImpl.findAllDirectors();
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona um novo diretor", description = "Insere um novo diretor no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Diretor adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void inserirDiretor(@RequestBody DiretorDTO diretorDTO){
        diretorServiceImpl.saveDirector(diretorDTO);
    }

    @PutMapping("/alterar")
    @Operation(summary = "Altera um diretor", description = "Atualiza os dados de um diretor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Diretor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public DiretorDTO alterarDiretor(@RequestBody DiretorDTO diretorDTO){
        return diretorServiceImpl.updateDirector(diretorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Exclui um diretor", description = "Remove um diretor do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirDiretor(@PathVariable("id") Integer id){
        diretorServiceImpl.deleteDirector(id);
        return ResponseEntity.ok().build();
    }



}
