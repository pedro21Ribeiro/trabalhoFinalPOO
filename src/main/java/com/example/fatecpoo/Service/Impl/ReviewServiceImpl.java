package com.example.fatecpoo.Service.Impl;

import com.example.fatecpoo.DTO.FilmeDTO.FilmeDTO;
import com.example.fatecpoo.DTO.ReviewDTO.ReviewDTO;
import com.example.fatecpoo.Entity.FilmeEntity;
import com.example.fatecpoo.Entity.ReviewEntity;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
import com.example.fatecpoo.Repository.FilmeRepository;
import com.example.fatecpoo.Repository.ReviewRepository;
import com.example.fatecpoo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private FilmeRepository filmeRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAllReviews(){
        List<ReviewEntity> reviews = reviewRepository.findAll();
        if(reviews.isEmpty()){
            throw new RegisterNotFound("Nenhum review cadastrado");
        }
        return reviews.stream().map(ReviewDTO::new).toList();
    }

    public List<ReviewDTO> findByFilme(Integer id){

        if(!filmeRepository.existsById(id)){
            throw new RegisterNotFound("Nenhuma Review encontrada");
        }
        List<ReviewEntity> reviews = reviewRepository.findByFilme_Id(id);
        return reviews.stream().map(ReviewDTO::new).toList();
    }

    public ReviewDTO saveReview(ReviewDTO review){
        ReviewEntity reviewEntity = new ReviewEntity(review);
        System.out.println(reviewEntity);
        ReviewEntity savedReview = reviewRepository.save(reviewEntity);
        return new ReviewDTO(savedReview);
    }


    public ReviewDTO updateReview(ReviewDTO reviewDTO){
        if(!reviewRepository.existsById(reviewDTO.getId())){
            throw new RegisterNotFound("Review não encontrada");
        }
        ReviewEntity reviewEntity = new ReviewEntity(reviewDTO);
        return new ReviewDTO(reviewRepository.save(reviewEntity));
    }


    public void deleteReview(Integer id){


        if(!reviewRepository.existsById(id)){
            throw new RegisterNotFound("Review não encontrada");
        }
        ReviewEntity reviewEntity = reviewRepository.findById(id).get();
        reviewRepository.delete(reviewEntity);
    }

}
