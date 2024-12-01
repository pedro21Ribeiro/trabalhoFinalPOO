package com.example.fatecpoo.Service.Impl;


import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.Entity.DiretorEntity;
import com.example.fatecpoo.Exceptions.DuplicateRegistry;
import com.example.fatecpoo.Exceptions.EmptyFieldException;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
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
        if(diretores.isEmpty()){
            throw new RegisterNotFound("Nenhum diretor adicionado");
        }
        return diretores.stream().map(DiretorDTO::new).toList();
    }

    @Override
    public DiretorDTO findDirectorById(Integer id) {
        if(id == null){
            throw new EmptyFieldException("O campo id é obrigatorio");
        }
        if(!diretorRepository.existsById(id)){
            throw new RegisterNotFound("Diretor não encontrado");
        }
        return new DiretorDTO(diretorRepository.getById(id));
    }

    @Override
    public DiretorDTO saveDirector(DiretorDTO diretorDTO) {
        if(diretorDTO.getNomeDiretor().isEmpty() || diretorDTO.getDataNascimentoDiretor().isEmpty()){
            throw new EmptyFieldException("O campo Nome Diretor e Data de Nascimento devem ser obrigatoriamente preenchidos");
        }
        if(diretorRepository.existsByNomeDiretor(diretorDTO.getNomeDiretor())){
            throw new DuplicateRegistry("Já Existe um diretor com esse nome");
        }

        DiretorEntity diretorEntity = new DiretorEntity(diretorDTO);
        DiretorEntity savedDiretor = diretorRepository.save(diretorEntity);
        return new DiretorDTO(savedDiretor);
    }

    @Override
    public void deleteDirector(Integer id) {
        if(id == null){
            throw new EmptyFieldException("O campo id é obrigatorio");
        }
        if(!diretorRepository.existsById(id)){
            throw new RegisterNotFound("Diretor não encontrado");
        }
        DiretorEntity diretorEntity = diretorRepository.findById(id).get();
        diretorRepository.delete(diretorEntity);
    }

    @Override
    public DiretorDTO updateDirector(DiretorDTO diretorDTO) {
        if(!diretorRepository.existsById(diretorDTO.getId())){
            throw new RegisterNotFound("Diretor não encontrado");
        }
        DiretorEntity diretorEntity = new DiretorEntity(diretorDTO);
        return new DiretorDTO(diretorRepository.save(diretorEntity));
    }
}
