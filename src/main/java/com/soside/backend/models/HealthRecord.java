package com.soside.backend.models;

import com.soside.backend.enums.HealthRecordType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "healthRecord")
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private LocalDate recordDate;

    @Enumerated(EnumType.STRING)
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

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CareHistory> careHistory;
}
