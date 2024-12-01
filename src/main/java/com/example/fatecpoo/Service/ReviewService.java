package com.example.fatecpoo.Service;

import com.example.fatecpoo.DTO.DiretorDTO.DiretorDTO;
import com.example.fatecpoo.DTO.ReviewDTO.ReviewDTO;
import com.example.fatecpoo.DTO.SinopseDTO.SinopseDTO;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Entity.ReviewEntity;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAllReviews();
    List<ReviewDTO> findByFilme(Integer id);
    ReviewDTO updateReview(ReviewDTO reviewDTO);
    ReviewDTO saveReview(ReviewDTO reviewDTO);
    void deleteReview(Integer id);
}
