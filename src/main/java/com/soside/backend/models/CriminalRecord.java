package com.soside.backend.models;
import com.soside.backend.enums.AppealStatus;
import com.soside.backend.enums.ParoleStatus;
import com.soside.backend.enums.RehabilitationStatus;
import com.soside.backend.enums.SentenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "criminalRecord")
public class CriminalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private LocalDate convictionDate;
    private String offenseDescription;
    private String courtName;
    private String judgeName;
    private String sentence;

    @Enumerated(EnumType.STRING)
    private SentenceType sentenceType;

    private String sentenceDuration;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private ParoleStatus paroleStatus;

    @Enumerated(EnumType.STRING)
    private RehabilitationStatus rehabilitationStatus;

    private String caseNumber;
    private String prosecutorName;
    private String defenseAttorneyName;

    @Enumerated(EnumType.STRING)
    private AppealStatus appealStatus;

    private String notes;
    private String authorizedBy;
    private LocalDateTime consultationDate;
    private String consultationReason;
}
