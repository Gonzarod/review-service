package com.evertix.reviewservice.config;
;
import com.evertix.reviewservice.entities.Complaint;
import com.evertix.reviewservice.entities.Review;
import com.evertix.reviewservice.model.User;
import com.evertix.reviewservice.repository.ComplaintRepository;
import com.evertix.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader {

    private ReviewRepository reviewRepository;
    private ComplaintRepository complaintRepository;
    private RestTemplate restTemplate;
    @Autowired
    public DataLoader(ComplaintRepository complaintRepository, ReviewRepository reviewRepository, RestTemplate restTemplate) {
        this.reviewRepository=reviewRepository;
        this.complaintRepository=complaintRepository;
        this.restTemplate=restTemplate;
        LoadData();
    }

    private void LoadData() {
        //
        //Fetch User Data (calls to user-service)}
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        //User userStudent = restTemplate.getForObject("http://user-service/api/users/username/jesus.student",User.class);
        //User userTeacher = restTemplate.getForObject("http://user-service/api/users/username/albert.teacher",User.class);

        User userStudent = restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/username/jesus.student",User.class);
        User userTeacher = restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/username/albert.teacher",User.class);

        List<Review> reviews = new ArrayList<Review>();
        reviews.add(new Review((short) 5,"Excelente profesor",userStudent.getId(), userTeacher.getId()));
        reviews.add(new Review((short) 4,"Buena clase",userStudent.getId(), userTeacher.getId()));
        reviews.add(new Review((short) 4,"Enseña bien",userStudent.getId(), userTeacher.getId()));
        this.reviewRepository.saveAll(reviews);

        List<Complaint> complaints = new ArrayList<Complaint>();
        complaints.add(new Complaint("Tardanza","Llego 5 minutos tarde",userStudent.getId(), userTeacher.getId()));
        complaints.add(new Complaint("Acosador","Me acosó",userTeacher.getId(), userStudent.getId()));
        this.complaintRepository.saveAll(complaints);
    }
}