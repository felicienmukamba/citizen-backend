package com.soside.backend.models;
import com.soside.backend.enums.CareType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "careHistory")
public class CareHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "health_record_id")
    private HealthRecord healthRecord;

    @Enumerated(EnumType.STRING)
    private CareType careType;

    private LocalDate careDate;
    private String careDescription;
    private String doctorName;
    private String medicalFacility;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    public CareType getCareType() {
        return careType;
    }

    public void setCareType(CareType careType) {
        this.careType = careType;
    }

    public LocalDate getCareDate() {
        return careDate;
    }

    public void setCareDate(LocalDate careDate) {
        this.careDate = careDate;
    }

    public String getCareDescription() {
        return careDescription;
    }

    public void setCareDescription(String careDescription) {
        this.careDescription = careDescription;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(String medicalFacility) {
        this.medicalFacility = medicalFacility;
    }
}
