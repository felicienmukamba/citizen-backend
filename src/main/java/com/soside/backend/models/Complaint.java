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
}
