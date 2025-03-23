package com.soside.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BirthRecordDTO {
    private Long id;
    private Long person_id;
    private String birthPlace;
    private LocalDate declarationDate;
    private String declarationPlace;
    private String fatherName;
    private String motherName;
    private String informantName;
    private String informantRelationship;
    private String registrationNumber;
    private String deliveryMethod;
    private String birthOrder;
    private String hospitalName;
}