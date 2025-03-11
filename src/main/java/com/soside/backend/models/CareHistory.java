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
}
