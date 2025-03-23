package com.soside.backend.dto;

import com.soside.backend.enums.ComplaintStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ComplaintDTO {
    private Long id;
    private Long plaintiffId;
    private Long accusedId;
    private LocalDate complaintDate;
    private String complaintDescription;
    private String complaintPlace;
    private String complaintType;
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