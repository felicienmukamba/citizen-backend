package com.soside.backend.dto;

import com.soside.backend.enums.AppealStatus;
import com.soside.backend.enums.ParoleStatus;
import com.soside.backend.enums.RehabilitationStatus;
import com.soside.backend.enums.SentenceType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CriminalRecordDTO {
    private Long id;
    private Long personId;
    private LocalDate convictionDate;
    private String offenseDescription;
    private String courtName;
    private String judgeName;
    private String sentence;
    private SentenceType sentenceType;
    private String sentenceDuration;
    private LocalDate releaseDate;
    private ParoleStatus paroleStatus;
    private RehabilitationStatus rehabilitationStatus;
    private String caseNumber;
    private String prosecutorName;
    private String defenseAttorneyName;
    private AppealStatus appealStatus;
    private String notes;
    private String authorizedBy;
    private LocalDateTime consultationDate;
    private String consultationReason;
}