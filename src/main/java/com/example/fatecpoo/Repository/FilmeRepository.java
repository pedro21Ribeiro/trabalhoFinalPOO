package com.example.fatecpoo.Repository;

import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Entity.SinopseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<FilmeEntity, Integer> {

    List<FilmeEntity> findByDiretorNomeDiretorContaining(String nomeDiretor);
    List<FilmeEntity> findByNomeFilmeContaining(String nomeFilme);
    List<FilmeEntity> findByScoreFilmeEquals(Double scoreFilme);

}
