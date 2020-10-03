package com.evertix.reviewservice.config;
;
import com.evertix.reviewservice.entities.Complaint;
import com.evertix.reviewservice.entities.Review;
import com.evertix.reviewservice.repository.ComplaintRepository;
import com.evertix.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader {

    private ReviewRepository reviewRepository;
    private ComplaintRepository complaintRepository;

    @Autowired
    public DataLoader(ComplaintRepository complaintRepository,ReviewRepository reviewRepository) {
        this.reviewRepository=reviewRepository;
        this.complaintRepository=complaintRepository;
        LoadData();
    }

    private void LoadData() {
        
        List<Review> reviews = new ArrayList<Review>();
        reviews.add(new Review((short) 5,"Excelente profesor", (long) 1,(long) 2));
        reviews.add(new Review((short) 4,"Buena clase",(long) 1,(long)2));
        reviews.add(new Review((short) 4,"Enseña bien",(long) 1,(long) 2));
        this.reviewRepository.saveAll(reviews);

        List<Complaint> complaints = new ArrayList<Complaint>();
        complaints.add(new Complaint("Tardanza","Llego 5 minutos tarde",(long) 1, (long) 2));
        complaints.add(new Complaint("Acosador","Me acosó",(long) 2, (long) 1));
        this.complaintRepository.saveAll(complaints);
    }
}