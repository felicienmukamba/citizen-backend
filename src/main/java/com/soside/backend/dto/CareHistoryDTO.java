package com.soside.backend.dto;

import com.soside.backend.enums.CareType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CareHistoryDTO {
    private Long id;
    private Long healthRecordId;
    private CareType careType;
    private LocalDate careDate;
    private String careDescription;
    private String doctorName;
    private String medicalFacility;
}