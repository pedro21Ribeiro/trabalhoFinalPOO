package com.example.fatecpoo.Service.Impl;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.SinopseEntity;
import com.example.fatecpoo.Repository.SinopseRepository;
import com.example.fatecpoo.Service.SinopseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SinopseServiceImpl implements SinopseService {

    private SinopseRepository sinopseRepository;

    @Autowired
    public SinopseServiceImpl(SinopseRepository sinopseRepository) {
        this.sinopseRepository = sinopseRepository;
    }

    @Override
    public SinopseDTO updateSinopse(SinopseDTO sinopseDTO) {
        SinopseEntity sinopseEntity = new SinopseEntity(sinopseDTO);
        return new SinopseDTO(sinopseRepository.save(sinopseEntity));
    }

    @Override
    public List<SinopseDTO> findSinopseByDescricao(String descricao) {
        return sinopseRepository.findByDescricaoContaining(descricao)
                .stream()
                .map(sinopse -> new SinopseDTO(sinopse))
                .collect(Collectors.toList());
    }

    @Override
    public SinopseDTO saveSinopse(SinopseDTO sinopseDTO) {
        SinopseEntity sinopseEntity = new SinopseEntity(sinopseDTO);
        SinopseEntity savedSinopse = sinopseRepository.save(sinopseEntity);
        return new SinopseDTO(savedSinopse);
    }

    @Override
    public void deleteSinopse(Integer id) {
        SinopseEntity sinopseEntity = sinopseRepository.findById(id).get();
        sinopseRepository.delete(sinopseEntity);
    }
}
