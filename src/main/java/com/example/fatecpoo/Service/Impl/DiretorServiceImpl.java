package com.example.fatecpoo.Service.Impl;


import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Repository.DiretorRepository;
import com.example.fatecpoo.Service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiretorServiceImpl implements DiretorService {


    private DiretorRepository diretorRepository;

    @Autowired
    public DiretorServiceImpl(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }
    @Override
    public List<DiretorDTO> findAllDirectors() {
        List<DiretorEntity> diretores = diretorRepository.findAll();
        return diretores.stream().map(DiretorDTO::new).toList();
    }

    @Override
    public DiretorDTO findDirectorById(Integer id) {
        return new DiretorDTO(diretorRepository.getById(id));
    }

    @Override
    public DiretorDTO saveDirector(DiretorDTO diretorDTO) {
        DiretorEntity diretorEntity = new DiretorEntity(diretorDTO);
        DiretorEntity savedDiretor = diretorRepository.save(diretorEntity);
        return new DiretorDTO(savedDiretor);
    }

    @Override
    public void deleteDirector(Integer id) {
        DiretorEntity diretorEntity = diretorRepository.findById(id).get();
        diretorRepository.delete(diretorEntity);
    }

    @Override
    public DiretorDTO updateDirector(DiretorDTO diretorDTO) {
        DiretorEntity diretorEntity = new DiretorEntity(diretorDTO);
        return new DiretorDTO(diretorRepository.save(diretorEntity));
    }
}
