package com.example.fatecpoo.Controller;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.Service.Impl.DiretorServiceImpl;
import com.example.fatecpoo.Service.Impl.FilmeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/filme")

public class FilmeController {
    private FilmeServiceImpl filmeServiceImpl;

    @Autowired
    public FilmeController(FilmeServiceImpl filmeServiceImpl) {
        this.filmeServiceImpl = filmeServiceImpl;
    }

    @GetMapping("/todos")
    public List<FilmeDTO> ListarTodos (){
        return filmeServiceImpl.findAllMovies();
    }

    @PostMapping("/adicionar")
    public void inserirFilme(@RequestBody FilmeDTO filmeDTO){
        filmeServiceImpl.saveMovie(filmeDTO);
    }

    @PutMapping("/alterar")
    public FilmeDTO alterarFilme(@RequestBody FilmeDTO filmeDTO){
        return filmeServiceImpl.updateMovie(filmeDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirFilme(@PathVariable("id") Integer id){
        filmeServiceImpl.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public FilmeDTO pegarFilmePeloId(@PathVariable("id") Integer id){
        return filmeServiceImpl.findMoviesById(id);
    }

    @GetMapping("/diretor/{diretor}")
    public List<FilmeDTO> listarFilmesPeloDiretor(@PathVariable("diretor") String diretor){
        return filmeServiceImpl.findMovieByDirector(diretor);
    }

    @GetMapping("/score/{score}")
    public List<FilmeDTO> listarFilmesPeloScore(@PathVariable("score") double score){
        return filmeServiceImpl.findMovieByScore(score);
    }

    @GetMapping("/nome/{nome}")
    public List<FilmeDTO> listarFilmePeloNome(@PathVariable("nome") String nome){
        return filmeServiceImpl.findMovieByMovieName(nome);
    }
}






