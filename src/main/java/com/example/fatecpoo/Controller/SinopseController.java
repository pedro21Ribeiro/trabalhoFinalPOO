package com.example.fatecpoo.Controller;


import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import com.example.fatecpoo.Service.Impl.SinopseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sinopse")
public class SinopseController {

    private SinopseServiceImpl sinopseServiceImpl;

    @Autowired
    public SinopseController(SinopseServiceImpl sinopseServiceImpl) {
        this.sinopseServiceImpl = sinopseServiceImpl;
    }

    @PostMapping("/pesquisar")
    public List<SinopseDTO> ListarSinopses(@RequestBody SinopseDTO sinopseDTO){
        return sinopseServiceImpl.findSinopseByDescricao(sinopseDTO.getDescricao());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirSinopse(@PathVariable("id") Integer id){
        sinopseServiceImpl.deleteSinopse(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/adicionar")
    public void InserirSinopse(@RequestBody SinopseDTO sinopseDTO){
        System.out.println(sinopseDTO.getDescricao());
        sinopseServiceImpl.saveSinopse(sinopseDTO);
    }

    @PutMapping("/alterar")
    public SinopseDTO alterarSinopse(@RequestBody SinopseDTO sinopseDTO){
        return sinopseServiceImpl.updateSinopse(sinopseDTO);
    }


}
