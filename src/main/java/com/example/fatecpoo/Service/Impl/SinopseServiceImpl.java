package com.example.fatecpoo.Service.Impl;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Entity.SinopseEntity;
import com.example.fatecpoo.Exceptions.EmptyFieldException;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
import com.example.fatecpoo.Repository.SinopseRepository;
import com.example.fatecpoo.Service.SinopseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
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
        if(!sinopseRepository.existsById(sinopseDTO.getId())){
            throw new RegisterNotFound("Sinopse não encontrada");
        }
        SinopseEntity sinopseEntity = new SinopseEntity(sinopseDTO);
        return new SinopseDTO(sinopseRepository.save(sinopseEntity));
    }

    @Override
    public List<SinopseDTO> findSinopseByDescricao(String descricao) {
        List<SinopseEntity> sinopse = sinopseRepository.findByDescricaoContaining(descricao);
        if(sinopse.isEmpty()){
            throw new RegisterNotFound("Nenhuma sinopse encontrada");
        }
        return sinopse
                .stream()
                .map(sinopseEntity -> new SinopseDTO(sinopseEntity))
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
        if(!sinopseRepository.existsById(id)){
            throw new RegisterNotFound("Sinopse não encontrada");
        }
        SinopseEntity sinopseEntity = sinopseRepository.findById(id).get();
        sinopseRepository.delete(sinopseEntity);
    }
}
