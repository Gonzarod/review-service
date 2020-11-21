package com.evertix.reviewservice.service.impl;


import com.evertix.reviewservice.entities.Complaint;
import com.evertix.reviewservice.model.User;
import com.evertix.reviewservice.repository.ComplaintRepository;
import com.evertix.reviewservice.service.ComplaintService;
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
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll().stream().map(complaint -> {
            User madeBy=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/users/"+complaint.getMadeById(),User.class);
            User reported=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/users/"+ complaint.getReportedId(),User.class);
            complaint.setMadeByModel(madeBy);
            complaint.setReportedModel(reported);
            return complaint;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<Complaint> getAllComplaintsPage(Pageable pageable) {
        Page<Complaint> page=complaintRepository.findAll(pageable);
        List<Complaint> result=page.getContent().stream().map(complaint -> {
            User madeBy=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+complaint.getMadeById(),User.class);
            User reported=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+ complaint.getReportedId(),User.class);
            complaint.setMadeByModel(madeBy);
            complaint.setReportedModel(reported);
            return complaint;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());
    }
/*
    @Override
    public Page<Complaint> getAllComplaintsByMadeById(Long userId, Pageable pageable) {
        return complaintRepository.findAllByMadeById(userId, pageable);
    }

    @Override
    public Page<Complaint> getAllComplaintsByReportedId(Long reportedId, Pageable pageable) {
        return complaintRepository.findAllByReportedId(reportedId, pageable);
    }

    @Override
    public Complaint createComplaint(Long madeById, Long reportedId, Complaint complaint) {
        return userRepository.findById(madeById).map(madeBy -> {
            return userRepository.findById(reportedId).map(reported -> {
                complaint.setMadeBy(madeBy);
                complaint.setReported(reported);
                return complaintRepository.save(complaint);
            }).orElseThrow(()-> new ResourceNotFoundException("Reported with Id: "+reportedId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("MadeBy with Id: "+madeById+" not found"));
    }

    @Override
    public Complaint updateComplaint(Long complaintId, Complaint complaintDetails) {
        return complaintRepository.findById(complaintId).map(complaint -> {
            complaint.setSubject(complaintDetails.getSubject());
            complaint.setDescription(complaintDetails.getDescription());
            return complaintRepository.save(complaint);
        }).orElseThrow(() -> new ResourceNotFoundException("Complaint with Id: "+complaintId+" not found"));
    }

    @Override
    public ResponseEntity<?> deleteComplaint(Long complaintId) {
        return complaintRepository.findById(complaintId).map(complaint -> {
            complaintRepository.delete(complaint);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Complaint with Id: "+complaintId+" not found"));
    }

 */



}
