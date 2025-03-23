package com.soside.backend.controllers;
import com.soside.backend.dto.ComplaintDTO;
import com.soside.backend.mappers.ComplaintMapper;
import com.soside.backend.models.Complaint;
import com.soside.backend.services.complaint.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @GetMapping
    public List<ComplaintDTO> getAllComplaints() {
        return complaintService.getAllComplaints().stream()
                .map(ComplaintMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> getComplaintById(@PathVariable Long id) {
        Complaint complaint = complaintService.getComplaintById(id);
        ComplaintDTO complaintDTO = ComplaintMapper.INSTANCE.toDTO(complaint);
        return ResponseEntity.ok(complaintDTO);
    }

    @PostMapping
    public ResponseEntity<ComplaintDTO> createComplaint(@RequestBody ComplaintDTO complaintDTO) {
        Complaint complaint = ComplaintMapper.INSTANCE.toEntity(complaintDTO);
        Complaint createdComplaint = complaintService.createComplaint(complaint);
        ComplaintDTO createdComplaintDTO = ComplaintMapper.INSTANCE.toDTO(createdComplaint);
        return ResponseEntity.ok(createdComplaintDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintDTO> updateComplaint(@PathVariable Long id, @RequestBody ComplaintDTO complaintDTO) {
        Complaint complaint = ComplaintMapper.INSTANCE.toEntity(complaintDTO);
        Complaint updatedComplaint = complaintService.updateComplaint(id, complaint);
        ComplaintDTO updatedComplaintDTO = ComplaintMapper.INSTANCE.toDTO(updatedComplaint);
        return ResponseEntity.ok(updatedComplaintDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.noContent().build();
    }
}