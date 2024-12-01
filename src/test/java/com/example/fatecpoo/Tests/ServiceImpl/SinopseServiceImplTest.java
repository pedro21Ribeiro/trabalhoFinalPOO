package com.example.fatecpoo.Tests.ServiceImpl;

import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import com.example.fatecpoo.Entity.SinopseEntity;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
import com.example.fatecpoo.Repository.SinopseRepository;
import com.example.fatecpoo.Service.Impl.SinopseServiceImpl;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@Component
class SinopseServiceImplTest {

    @InjectMocks
    private SinopseServiceImpl sinopseService;

    @Mock
    private SinopseRepository sinopseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void updateSinopse_ShouldThrowException_WhenSinopseDoesNotExist() {
        SinopseDTO sinopseDTO = new SinopseDTO(2, "Descrição Inválida");

        Mockito.when(sinopseRepository.existsById(2)).thenReturn(false);

        Assertions.assertThrows(RegisterNotFound.class, () -> sinopseService.updateSinopse(sinopseDTO));
        Mockito.verify(sinopseRepository, Mockito.never()).save(Mockito.any());
    }


    @Test
    public void findSinopseByDescricao() {

        SinopseEntity sinopseEntity = new SinopseEntity(1, "Uma sinopse teste!");
        Mockito.when(sinopseRepository.findByDescricaoContaining("a"))
                .thenReturn(Collections.singletonList(sinopseEntity));

        List<SinopseDTO> sinopses = sinopseService.findSinopseByDescricao("a");

        Assertions.assertFalse(sinopses.isEmpty());
        Assertions.assertEquals("Uma sinopse teste!", sinopses.get(0).getDescricao());
        Mockito.verify(sinopseRepository).findByDescricaoContaining("a");

    }

    @Test
    void saveSinopse_ShouldSaveAndReturnSinopse() {
        // Arrange
        SinopseDTO sinopseDTO = new SinopseDTO(null, "Descrição Nova");
        SinopseEntity sinopseEntity = new SinopseEntity(sinopseDTO);

        Mockito.when(sinopseRepository.save(Mockito.any(SinopseEntity.class))).thenReturn(sinopseEntity);

        // Act
        SinopseDTO savedSinopse = sinopseService.saveSinopse(sinopseDTO);

        // Assert
        Assertions.assertEquals("Descrição Nova", savedSinopse.getDescricao());
        Mockito.verify(sinopseRepository).save(Mockito.any(SinopseEntity.class));
    }


    @Test
    void deleteSinopse_ShouldDeleteSuccessfully_WhenSinopseExists() {
        // Arrange
        SinopseEntity sinopseEntity = new SinopseEntity(1, "Descrição Existente");

        Mockito.when(sinopseRepository.existsById(1)).thenReturn(true);
        Mockito.when(sinopseRepository.findById(1)).thenReturn(Optional.of(sinopseEntity));

        sinopseService.deleteSinopse(1);

        Mockito.verify(sinopseRepository).delete(Mockito.any(SinopseEntity.class));
    }

    @Test
    void deleteSinopse_ShouldThrowException_WhenSinopseNotFound() {
        Mockito.when(sinopseRepository.existsById(2)).thenReturn(false);

        Assertions.assertThrows(RegisterNotFound.class, () -> sinopseService.deleteSinopse(2));
        Mockito.verify(sinopseRepository, Mockito.never()).delete(Mockito.any());
    }

}