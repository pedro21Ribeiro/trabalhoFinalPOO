package com.example.fatecpoo.Service;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;

import java.util.List;

public interface FilmeService{
    // Adicione os métodos que serão implementados em FilmeServiceImpl
    List<FilmeDTO> findAllMovies();

    FilmeDTO findMoviesById(Integer id);

    FilmeDTO saveMovie(FilmeDTO filme);

    void deleteMovie(Integer id);

    FilmeDTO updateMovie(FilmeDTO FilmeDTO);

    List<FilmeDTO> findMovieByDirector(String nomeDiretor);
    List<FilmeDTO> findMovieByMovieName(String filme);
    List<FilmeDTO> findMovieByScore(Double score);



}
