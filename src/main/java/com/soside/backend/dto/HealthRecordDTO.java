package com.soside.backend.dto;

import com.soside.backend.enums.HealthRecordType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HealthRecordDTO {
    private Long id;
    private Long personId;
    private LocalDate recordDate;
    private HealthRecordType recordType;
    private String details;
    private String result;
    private String doctorName;
    private String doctorSpeciality;
    private String medicalFacility;
    private String medicalHistory;
    private String currentMedication;
    private String chronicConditions;
    private String allergies;
    private String disabilities;
    private String vaccinations;
    private String surgeries;
    private String hospitalizations;
    private String healthInsuranceNumber;
}