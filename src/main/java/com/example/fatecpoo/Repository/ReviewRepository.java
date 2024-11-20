package com.example.fatecpoo.Repository;

import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Integer> {
    List<ReviewEntity> findByFilme_Id(Integer Id);
}
