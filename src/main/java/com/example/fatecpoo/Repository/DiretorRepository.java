package com.example.fatecpoo.Repository;

import com.example.fatecpoo.Entity.DiretorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorRepository extends JpaRepository<DiretorEntity, Integer> {
    boolean existsByNomeDiretor(String nome);
}
