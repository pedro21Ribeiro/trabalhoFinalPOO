package com.example.fatecpoo.Service.Impl;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Repository.DiretorRepository;
import com.example.fatecpoo.Repository.FilmeRepository;
import com.example.fatecpoo.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FilmeServiceImpl implements FilmeService {

    private FilmeRepository filmeRepository;

    @Autowired
    public FilmeServiceImpl(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Override
    public List<FilmeDTO> findAllMovies(){
        List<FilmeEntity> filmes = filmeRepository.findAll();
        return filmes.stream().map(FilmeDTO::new).toList();
    }

    public FilmeDTO findMoviesById(Integer id){
        FilmeEntity filme = filmeRepository.getReferenceById(id);
        return new FilmeDTO(filme);
    }

    public FilmeDTO saveMovie(FilmeDTO filme){
        FilmeEntity filmeEntity = new FilmeEntity(filme);
        FilmeEntity savedFilme = filmeRepository.save(filmeEntity);
        return new FilmeDTO(savedFilme);
    }

    public void deleteMovie(Integer id){
        FilmeEntity filmeEntity = filmeRepository.findById(id).get();
        filmeRepository.delete(filmeEntity);
    }

    public FilmeDTO updateMovie(FilmeDTO filmeDTO){
        FilmeEntity filmeEntity = new FilmeEntity(filmeDTO);
        return new FilmeDTO(filmeRepository.save(filmeEntity));
    }

    public List<FilmeDTO> findMovieByDirector(String nomeDiretor){
        List<FilmeEntity> filmes = filmeRepository.findByDiretorNomeDiretorContaining(nomeDiretor);
        return filmes.stream().map(FilmeDTO::new).toList();
    }
    public List<FilmeDTO> findMovieByMovieName(String filme){
        List<FilmeEntity> filmes = filmeRepository.findByNomeFilmeContaining(filme);
        System.out.println(filmes);
        return filmes.stream().map(FilmeDTO::new).toList();
    }
    public List<FilmeDTO> findMovieByScore(Double score){
        List<FilmeEntity> filmes = filmeRepository.findByScoreFilmeEquals(score);
        return filmes.stream().map(FilmeDTO::new).toList();
    }
}
