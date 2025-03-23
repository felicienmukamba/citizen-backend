package com.soside.backend.models;

import com.soside.backend.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plaintiff_id")
    private Person plaintiff;

    @ManyToOne
    @JoinColumn(name = "accused_id")
    private Person accused;

    private LocalDate complaintDate;
    private String complaintDescription;
    private String complaintPlace;
    private String complaintType;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus;

    private String complaintDetails;
    private String complainantName;
    private String complainantContact;
    private String accusedName;
    private String accusedContact;
    private String witnesses;
    private String evidence;
    private String policeReportNumber;
    private String previousComplaints;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(Person plaintiff) {
        this.plaintiff = plaintiff;
    }

    public Person getAccused() {
        return accused;
    }

    public void setAccused(Person accused) {
        this.accused = accused;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public String getComplaintPlace() {
        return complaintPlace;
    }

    public void setComplaintPlace(String complaintPlace) {
        this.complaintPlace = complaintPlace;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public String getComplainantName() {
        return complainantName;
    }

    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public String getComplainantContact() {
        return complainantContact;
    }

    public void setComplainantContact(String complainantContact) {
        this.complainantContact = complainantContact;
    }

    public String getAccusedName() {
        return accusedName;
    }

    public void setAccusedName(String accusedName) {
        this.accusedName = accusedName;
    }

    public String getAccusedContact() {
        return accusedContact;
    }

    public void setAccusedContact(String accusedContact) {
        this.accusedContact = accusedContact;
    }

    public String getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(String witnesses) {
        this.witnesses = witnesses;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getPoliceReportNumber() {
        return policeReportNumber;
    }

    public void setPoliceReportNumber(String policeReportNumber) {
        this.policeReportNumber = policeReportNumber;
    }

    public String getPreviousComplaints() {
        return previousComplaints;
    }

    public void setPreviousComplaints(String previousComplaints) {
        this.previousComplaints = previousComplaints;
    }
}