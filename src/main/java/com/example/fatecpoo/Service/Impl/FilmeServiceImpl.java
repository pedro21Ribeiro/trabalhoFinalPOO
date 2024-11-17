package com.example.fatecpoo.Service.Impl;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Exceptions.EmptyFieldException;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
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
        if(filmes.isEmpty()){
            throw new RegisterNotFound("Nenhum filme cadastrado");
        }
        return filmes.stream().map(FilmeDTO::new).toList();
    }

    public FilmeDTO findMoviesById(Integer id){

        if(!filmeRepository.existsById(id)){
            throw new RegisterNotFound("Filme não encontrado");
        }
        FilmeEntity filme = filmeRepository.getReferenceById(id);
        return new FilmeDTO(filme);
    }

    public FilmeDTO saveMovie(FilmeDTO filme){
        FilmeEntity filmeEntity = new FilmeEntity(filme);
        FilmeEntity savedFilme = filmeRepository.save(filmeEntity);
        return new FilmeDTO(savedFilme);
    }

    public void deleteMovie(Integer id){


        if(!filmeRepository.existsById(id)){
            throw new RegisterNotFound("Filme não encontrado");
        }
        FilmeEntity filmeEntity = filmeRepository.findById(id).get();
        filmeRepository.delete(filmeEntity);
    }

    public FilmeDTO updateMovie(FilmeDTO filmeDTO){
        if(!filmeRepository.existsById(filmeDTO.getId())){
            throw new RegisterNotFound("Filme não encontrado");
        }
        FilmeEntity filmeEntity = new FilmeEntity(filmeDTO);
        return new FilmeDTO(filmeRepository.save(filmeEntity));
    }

    public List<FilmeDTO> findMovieByDirector(String nomeDiretor){
        if(nomeDiretor.isEmpty()){
            throw new EmptyFieldException("O campo nome do Diretor é obrigatório");
        }
        List<FilmeEntity> filmes = filmeRepository.findByDiretorNomeDiretorContaining(nomeDiretor);
        if(!filmes.isEmpty()){
            throw new RegisterNotFound("Filmes não encontrados");
        }
        return filmes.stream().map(FilmeDTO::new).toList();
    }
    public List<FilmeDTO> findMovieByMovieName(String filme){
        if(filme.isEmpty()){
            throw new EmptyFieldException("O campo nome do filme é obrigatório");
        }
        List<FilmeEntity> filmes = filmeRepository.findByNomeFilmeContaining(filme);
        if(!filmes.isEmpty()){
            throw new RegisterNotFound("Filmes não encontrados");
        }
        return filmes.stream().map(FilmeDTO::new).toList();
    }
    public List<FilmeDTO> findMovieByScore(Double score){
        if(score == null){
            throw new EmptyFieldException("O campo score do filme é obrigatório");
        }
        List<FilmeEntity> filmes = filmeRepository.findByScoreFilmeEquals(score);
        if(!filmes.isEmpty()){
            throw new RegisterNotFound("Filmes não encontrados");
        }
        return filmes.stream().map(FilmeDTO::new).toList();
    }
}
