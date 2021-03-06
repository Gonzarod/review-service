package com.evertix.reviewservice.service;

import com.evertix.reviewservice.entities.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ComplaintService {

    List<Complaint> getAllComplaints();
    Page<Complaint> getAllComplaintsPage(Pageable pageable);
    //Page<Complaint> getAllComplaintsByMadeById(Long madeById, Pageable pageable);
    //Page<Complaint> getAllComplaintsByReportedId(Long reportedId, Pageable pageable);
    //Complaint createComplaint(Long madeById,Long reportedId, Complaint complaint);
    //Complaint updateComplaint(Long complaintId, Complaint complaintDetails);
    //ResponseEntity<?> deleteComplaint(Long complaintId);
}
