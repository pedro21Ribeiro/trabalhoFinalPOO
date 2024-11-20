package com.example.fatecpoo.Controller;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.Infra.Security.SecurityConfig;
import com.example.fatecpoo.Service.Impl.DiretorServiceImpl;
import com.example.fatecpoo.Service.Impl.FilmeServiceImpl;
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
@RequestMapping(value = "/filme")
@Tag(name = "Filme", description = "Controller para a inserção, alteração, consulta e deleção de filmes.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class FilmeController {
    private FilmeServiceImpl filmeServiceImpl;

    @Autowired
    public FilmeController(FilmeServiceImpl filmeServiceImpl) {
        this.filmeServiceImpl = filmeServiceImpl;
    }

    @GetMapping("/todos")
    @Operation(summary = "Lista todos os filmes", description = "Retorna uma lista de todos os filmes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<FilmeDTO> ListarTodos (){
        return filmeServiceImpl.findAllMovies();
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona um novo filme", description = "Insere um novo filme no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filme adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void inserirFilme(@RequestBody FilmeDTO filmeDTO){
        filmeServiceImpl.saveMovie(filmeDTO);
    }

    @PutMapping("/alterar")
    @Operation(summary = "Altera um filme", description = "Atualiza os dados de um filme existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public FilmeDTO alterarFilme(@RequestBody FilmeDTO filmeDTO){
        return filmeServiceImpl.updateMovie(filmeDTO);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Exclui um filme", description = "Remove um filme do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> excluirFilme(@PathVariable("id") Integer id){
        filmeServiceImpl.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera um filme pelo ID", description = "Retorna os dados de um filme específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme recuperado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public FilmeDTO pegarFilmePeloId(@PathVariable("id") Integer id){
        return filmeServiceImpl.findMoviesById(id);
    }

    @GetMapping("/diretor/{diretor}")
    @Operation(summary = "Lista filmes pelo diretor", description = "Retorna uma lista de filmes dirigidos por um diretor específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<FilmeDTO> listarFilmesPeloDiretor(@PathVariable("diretor") String diretor){
        return filmeServiceImpl.findMovieByDirector(diretor);
    }

    @GetMapping("/score/{score}")
    @Operation(summary = "Lista filmes pelo score", description = "Retorna uma lista de filmes com uma pontuação específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<FilmeDTO> listarFilmesPeloScore(@PathVariable("score") double score){
        return filmeServiceImpl.findMovieByScore(score);
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Lista filmes pelo nome", description = "Retorna uma lista de filmes com um nome específico ou parte do nome.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<FilmeDTO> listarFilmePeloNome(@PathVariable("nome") String nome){
        return filmeServiceImpl.findMovieByMovieName(nome);
    }
}






