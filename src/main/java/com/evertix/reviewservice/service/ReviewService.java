package com.evertix.reviewservice.service;

import com.evertix.reviewservice.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    Page<Review> getAllReviewsPage(Pageable pageable);
    List<Review> getAllReviews();
    //Page<Review> getReviewsByTeacher(Long teacherId, Pageable pageable);
    //Review createReview(Long studentId,Long teacherId,Review review);
    //Review updateReview(Long reviewId, Review reviewDetails);
    //ResponseEntity<?> deleteReview(Long reviewId);
}
