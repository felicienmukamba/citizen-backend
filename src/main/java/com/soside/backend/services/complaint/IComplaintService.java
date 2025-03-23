package com.soside.backend.services.complaint;

import com.soside.backend.models.Complaint;

import java.util.List;

public interface IComplaintService {
    Complaint createComplaint(Complaint complaint);
    Complaint updateComplaint(Long id, Complaint complaint);
    void deleteComplaint(Long id);
    Complaint getComplaintById(Long id);
    List<Complaint> getAllComplaints();
}