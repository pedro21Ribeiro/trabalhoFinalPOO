package com.example.fatecpoo.Tests.ServiceImpl;

import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Repository.FilmeRepository;
import com.example.fatecpoo.Service.Impl.FilmeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@Component
class FilmeServiceImplTest {


    @InjectMocks
    private FilmeServiceImpl filmeService;

    @Mock
    private FilmeRepository filmeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllMovies() {

     /*   List<FilmeEntity> filmesMock = List.of(new FilmeEntity(1, "Filme Teste 1", 5, "imagem.com", 1, 1),
                new FilmeEntity(2, "Filme Teste 2", 3, "imagem2.com", 2, DiretorEntity));
        Mockito.when(filmeRepository.findAll()).thenReturn(filmesMock);


        List<FilmeDTO> filmes = filmeService.findAllMovies();


        assertEquals(2, filmes.size());
        verify(filmeRepository, times(1)).findAll(); */
    }

    @Test
    void findMoviesById() {
    }

    @Test
    void saveMovie() {
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void findMovieByDirector() {
    }

    @Test
    void findMovieByMovieName() {
    }

    @Test
    void findMovieByScore() {
    }
}