package com.soside.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deathRecord")
public class DeathRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String deathPlace;
    private String deathDate;
    private String causeOfDeath;
    private String deceasedName;
    private String deceasedAge;
    private String deceasedGender;
    private String declarationDate;
    private String declarationPlace;
    private String informantName;
    private String informantRelationship;
    private String funeralPlace;
    private String cemeteryName;


}
