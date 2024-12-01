package com.example.fatecpoo.Tests.ServiceImpl;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
import com.example.fatecpoo.Repository.DiretorRepository;
import com.example.fatecpoo.Service.Impl.DiretorServiceImpl;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@Component
class DiretorServiceImplTest {


    @InjectMocks
    private DiretorServiceImpl diretorService;

    @Mock
    private DiretorRepository diretorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test

    void findAllDirectors_ShouldReturnList_WhenDirectorsExist() {
        List<DiretorEntity> diretorEntities = List.of(new DiretorEntity(1, "Diretor Teste", "01-01-1980"));
        Mockito.when(diretorRepository.findAll()).thenReturn(diretorEntities);

        List<DiretorDTO> result = diretorService.findAllDirectors();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Diretor Teste", result.get(0).getNomeDiretor());
        Mockito.verify(diretorRepository).findAll();
    }


    @Test
    void findAllDirectors_ShouldThrowException_WhenNoDirectorsExist() {

        Mockito.when(diretorRepository.findAll()).thenReturn(List.of());

        Assertions.assertThrows(RegisterNotFound.class, () -> diretorService.findAllDirectors());
        Mockito.verify(diretorRepository).findAll();
    }

    @Test
    void findDirectorById_ShouldReturnDirector_WhenIdExists() {

        DiretorEntity diretorEntity = new DiretorEntity(1, "Diretor tESTE", "01-01-1980");
        Mockito.when(diretorRepository.existsById(1)).thenReturn(true);
        Mockito.when(diretorRepository.getById(1)).thenReturn(diretorEntity);


        DiretorDTO result = diretorService.findDirectorById(1);


        Assertions.assertEquals("Diretor Teste", result.getNomeDiretor());
        Mockito.verify(diretorRepository).existsById(1);
        Mockito.verify(diretorRepository).getById(1);
    }

    @Test
    void findDirectorById_ShouldThrowException_WhenIdDoesNotExist() {
        Mockito.when(diretorRepository.existsById(1)).thenReturn(false);

        Assertions.assertThrows(RegisterNotFound.class, () -> diretorService.findDirectorById(1));
        Mockito.verify(diretorRepository).existsById(1);
    }

    @Test
    void saveDirector_ShouldSaveSuccessfully_WhenDataIsValid() {

        DiretorDTO diretorDTO = new DiretorDTO(null, "Diretor teste", "01-01-1980");
        DiretorEntity diretorEntity = new DiretorEntity(diretorDTO);
        Mockito.when(diretorRepository.save(Mockito.any(DiretorEntity.class))).thenReturn(diretorEntity);

        DiretorDTO result = diretorService.saveDirector(diretorDTO);

        Assertions.assertEquals("Diretor teste", result.getNomeDiretor());
        Mockito.verify(diretorRepository).save(Mockito.any(DiretorEntity.class));
    }

    @Test
    void deleteDirector_ShouldDeleteSuccessfully_WhenIdExists() {

        Mockito.when(diretorRepository.existsById(1)).thenReturn(true);
        DiretorEntity diretorEntity = new DiretorEntity(1, "Diretor Teste", "01-01-1980");
        Mockito.when(diretorRepository.findById(1)).thenReturn(Optional.of(diretorEntity));

        diretorService.deleteDirector(1);

        Mockito.verify(diretorRepository).delete(Mockito.any(DiretorEntity.class));
    }

    @Test
    void deleteDirector_ShouldThrowException_WhenIdDoesNotExist() {
        Mockito.when(diretorRepository.existsById(1)).thenReturn(false);

        Assertions.assertThrows(RegisterNotFound.class, () -> diretorService.deleteDirector(1));
        Mockito.verify(diretorRepository, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void updateDirector_ShouldUpdateSuccessfully_WhenIdExists() {
        DiretorDTO diretorDTO = new DiretorDTO(1, "Diretor teste", "01-01-1980");
        DiretorEntity diretorEntity = new DiretorEntity(diretorDTO);
        Mockito.when(diretorRepository.existsById(1)).thenReturn(true);
        Mockito.when(diretorRepository.save(Mockito.any(DiretorEntity.class))).thenReturn(diretorEntity);

        DiretorDTO result = diretorService.updateDirector(diretorDTO);

        Assertions.assertEquals("Diretor teste", result.getNomeDiretor());
        Mockito.verify(diretorRepository).save(Mockito.any(DiretorEntity.class));
    }

    @Test
    void updateDirector_ShouldThrowException_WhenIdDoesNotExist() {

        DiretorDTO diretorDTO = new DiretorDTO(2, "Diretor teste 2", "02-02-1990");
        Mockito.when(diretorRepository.existsById(2)).thenReturn(false);

        Assertions.assertThrows(RegisterNotFound.class, () -> diretorService.updateDirector(diretorDTO));
        Mockito.verify(diretorRepository, Mockito.never()).save(Mockito.any());
    }
}