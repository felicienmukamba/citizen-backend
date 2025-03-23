package com.soside.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeathRecordDTO {
    private Long id;
    private Long personId;
    private String deathPlace;
    private LocalDate deathDate;
    private String causeOfDeath;
    private String deceasedName;
    private Integer deceasedAge;
    private String deceasedGender;
    private LocalDate declarationDate;
    private String declarationPlace;
    private String informantName;
    private String informantRelationship;
    private String funeralPlace;
    private String cemeteryName;
}