package com.soside.backend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "birthRecord")
public class BirthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String birthPlace;
    private String declarationDate;
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
