package com.example.fatecpoo.Tests.ServiceImpl;

import com.example.fatecpoo.DTO.ReviewDTO.ReviewDTO;
import com.example.fatecpoo.Entity.ReviewEntity;
import com.example.fatecpoo.Exceptions.RegisterNotFound;
import com.example.fatecpoo.Repository.FilmeRepository;
import com.example.fatecpoo.Repository.ReviewRepository;
import com.example.fatecpoo.Service.Impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private FilmeRepository filmeRepository;

    private ReviewEntity reviewEntity;

    @BeforeEach
    void setUp() {
        reviewEntity = new ReviewEntity();
        reviewEntity.setId(1);
        reviewEntity.setComentario("Ótimo filme!");
        reviewEntity.setEstrela(5);
    }

    @Test
    void findAllReviews_shouldReturnListOfReviews() {
        when(reviewRepository.findAll()).thenReturn(List.of(reviewEntity));

        List<ReviewDTO> result = reviewService.findAllReviews();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ótimo filme!", result.get(0).getComentario());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void findAllReviews_shouldThrowExceptionIfEmpty() {
        when(reviewRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(RegisterNotFound.class, () -> reviewService.findAllReviews());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void findByFilme_shouldReturnReviewsForFilme() {
        when(filmeRepository.existsById(1)).thenReturn(true);
        when(reviewRepository.findByFilme_Id(1)).thenReturn(List.of(reviewEntity));

        List<ReviewDTO> result = reviewService.findByFilme(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ótimo filme!", result.get(0).getComentario());
        verify(filmeRepository, times(1)).existsById(1);
        verify(reviewRepository, times(1)).findByFilme_Id(1);
    }

    @Test
    void findByFilme_shouldThrowExceptionIfFilmeDoesNotExist() {
        when(filmeRepository.existsById(1)).thenReturn(false);

        assertThrows(RegisterNotFound.class, () -> reviewService.findByFilme(1));
        verify(filmeRepository, times(1)).existsById(1);
    }

    @Test
    void saveReview_shouldSaveAndReturnReview() {
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(reviewEntity);

        ReviewDTO reviewDTO = new ReviewDTO(reviewEntity);
        ReviewDTO result = reviewService.saveReview(reviewDTO);

        assertNotNull(result);
        assertEquals("Ótimo filme!", result.getComentario());
        verify(reviewRepository, times(1)).save(any(ReviewEntity.class));
    }

    @Test
    void updateReview_shouldUpdateAndReturnReview() {
        when(reviewRepository.existsById(1)).thenReturn(true);
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(reviewEntity);

        ReviewDTO reviewDTO = new ReviewDTO(reviewEntity);
        ReviewDTO result = reviewService.updateReview(reviewDTO);

        assertNotNull(result);
        assertEquals("Ótimo filme!", result.getComentario());
        verify(reviewRepository, times(1)).existsById(1);
        verify(reviewRepository, times(1)).save(any(ReviewEntity.class));
    }

    @Test
    void updateReview_shouldThrowExceptionIfReviewDoesNotExist() {
        when(reviewRepository.existsById(1)).thenReturn(false);

        ReviewDTO reviewDTO = new ReviewDTO(reviewEntity);
        assertThrows(RegisterNotFound.class, () -> reviewService.updateReview(reviewDTO));
        verify(reviewRepository, times(1)).existsById(1);
    }

    @Test
    void deleteReview_shouldDeleteReview() {
        when(reviewRepository.existsById(1)).thenReturn(true);

        reviewService.deleteReview(1);

        verify(reviewRepository, times(1)).existsById(1);
        verify(reviewRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteReview_shouldThrowExceptionIfReviewDoesNotExist() {
        when(reviewRepository.existsById(1)).thenReturn(false);

        assertThrows(RegisterNotFound.class, () -> reviewService.deleteReview(1));
        verify(reviewRepository, times(1)).existsById(1);
        verify(reviewRepository, times(0)).deleteById(1);
    }
}