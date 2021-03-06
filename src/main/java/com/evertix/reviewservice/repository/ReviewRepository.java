package com.evertix.reviewservice.repository;

import com.evertix.reviewservice.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> getAllByTeacherId(Long teacherId, Pageable pageable);
}
