package com.soside.backend.services.complaint;

import com.soside.backend.exceptions.ResourceNotFoundException;
import com.soside.backend.models.Complaint;
import com.soside.backend.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint updateComplaint(Long id, Complaint complaint) {
        Complaint existingComplaint = complaintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
        existingComplaint.setComplaintDate(complaint.getComplaintDate());
        existingComplaint.setComplaintDescription(complaint.getComplaintDescription());
        existingComplaint.setComplaintPlace(complaint.getComplaintPlace());
        existingComplaint.setComplaintType(complaint.getComplaintType());
        existingComplaint.setComplaintStatus(complaint.getComplaintStatus());
        existingComplaint.setComplaintDetails(complaint.getComplaintDetails());
        existingComplaint.setComplainantName(complaint.getComplainantName());
        existingComplaint.setComplainantContact(complaint.getComplainantContact());
        existingComplaint.setAccusedName(complaint.getAccusedName());
        existingComplaint.setAccusedContact(complaint.getAccusedContact());
        existingComplaint.setWitnesses(complaint.getWitnesses());
        existingComplaint.setEvidence(complaint.getEvidence());
        existingComplaint.setPoliceReportNumber(complaint.getPoliceReportNumber());
        existingComplaint.setPreviousComplaints(complaint.getPreviousComplaints());
        return complaintRepository.save(existingComplaint);
    }

    @Override
    public void deleteComplaint(Long id) {
        Complaint existingComplaint = complaintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
        complaintRepository.delete(existingComplaint);
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
}