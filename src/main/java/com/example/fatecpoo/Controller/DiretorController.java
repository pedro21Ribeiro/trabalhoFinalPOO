package com.example.fatecpoo.Controller;


import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.Repository.DiretorRepository;
import com.example.fatecpoo.Service.Impl.DiretorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/diretor")
public class DiretorController {


    private DiretorServiceImpl diretorServiceImpl;

    @Autowired
    public DiretorController(DiretorServiceImpl diretorServiceImpl) {
        this.diretorServiceImpl = diretorServiceImpl;
    }

    @GetMapping("/todos")
    public List<DiretorDTO>  ListarTodos (){
        return diretorServiceImpl.findAllDirectors();
    }

    @PostMapping("/adicionar")
    public void inserirDiretor(@RequestBody DiretorDTO diretorDTO){
        diretorServiceImpl.saveDirector(diretorDTO);
    }

    @PutMapping("/alterar")
    public DiretorDTO alterarDiretor(@RequestBody DiretorDTO diretorDTO){
        return diretorServiceImpl.updateDirector(diretorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirDiretor(@PathVariable("id") Integer id){
        diretorServiceImpl.deleteDirector(id);
        return ResponseEntity.ok().build();
    }



}
