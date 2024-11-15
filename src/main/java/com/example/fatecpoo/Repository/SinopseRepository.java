package com.example.fatecpoo.Repository;

import com.example.fatecpoo.Entity.SinopseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SinopseRepository extends JpaRepository<SinopseEntity, Integer> {

    List<SinopseEntity> findByDescricaoContaining(String descricaoSinopse);


}
