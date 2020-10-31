package com.evertix.reviewservice.service.impl;

import com.evertix.reviewservice.entities.Review;
import com.evertix.reviewservice.model.User;
import com.evertix.reviewservice.repository.ReviewRepository;
import com.evertix.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Review> getAllReviews() {

        return reviewRepository.findAll().stream().map(review -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+review.getStudentId(),User.class);
            //User teacher=restTemplate.getForObject("https://user-service/api/users/"+ review.getTeacherId(),User.class);
            User student=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+review.getStudentId(),User.class);
            User teacher=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+ review.getTeacherId(),User.class);
            review.setStudentModel(student);
            review.setTeacherModel(teacher);
            return review;
        }).collect(Collectors.toList());

    }


    @Override
    public Page<Review> getAllReviewsPage(Pageable pageable) {

        Page<Review> page=reviewRepository.findAll(pageable);
        List<Review> result=page.getContent().stream().map(review -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+review.getStudentId()+"/",User.class);
            //User teacher=restTemplate.getForObject("https://user-service/api/users/"+ review.getTeacherId()+"/",User.class);
            User student=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+review.getStudentId()+"/",User.class);
            User teacher=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+ review.getTeacherId()+"/",User.class);
            review.setStudentModel(student);
            review.setTeacherModel(teacher);
            return review;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());

    }


    /*
    @Override
    public Page<Review> getReviewsByTeacher(Long teacherId, Pageable pageable) {
        return reviewRepository.getAllByTeacherId(teacherId,pageable);
    }

    @Override
    public Review createReview(Long studentId,Long teacherId,Review review) {
        return userRepository.findById(studentId).map(student ->{
            return userRepository.findById(teacherId).map(teacher ->{
                review.setStudent(student);
                review.setTeacher(teacher);
                return reviewRepository.save(review);
            }).orElseThrow(()-> new ResourceNotFoundException("Teacher with id: "+teacherId+"not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Student with id: "+studentId+"not found"));
    }

    @Override
    public Review updateReview(Long reviewId, Review reviewDetails) {
        return reviewRepository.findById(reviewId).map(review ->{
            review.setDescription(reviewDetails.getDescription());
            review.setStars(reviewDetails.getStars());
            return reviewRepository.save(review);
        }).orElseThrow(()-> new ResourceNotFoundException("Review with id: "+reviewId+"not found"));
    }

    @Override
    public ResponseEntity<?> deleteReview(Long reviewId) {
        return reviewRepository.findById(reviewId).map(review ->{
            reviewRepository.delete(review);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Review with id: "+reviewId+"not found"));
    }

     */

}
