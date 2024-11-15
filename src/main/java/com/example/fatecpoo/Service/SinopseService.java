package com.example.fatecpoo.Service;

import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;

import java.util.List;

public interface SinopseService {

    // Métodos que serão implementados em SinopseServiceImpl


    SinopseDTO updateSinopse(SinopseDTO sinopseDTO);
    List<SinopseDTO> findSinopseByDescricao(String descricao);
    SinopseDTO saveSinopse(SinopseDTO sinopse);
    void deleteSinopse(Integer id);

}
