package com.example.fatecpoo.Service;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;

import java.util.List;

public interface DiretorService {

    // Adicione os métodos que serão implementados em DiretorServiceImpl
    List<DiretorDTO> findAllDirectors();

    DiretorDTO findDirectorById(Integer id);

    DiretorDTO saveDirector(DiretorDTO diretor);

    void deleteDirector(Integer id);

    DiretorDTO updateDirector(DiretorDTO diretorDTO);
}
