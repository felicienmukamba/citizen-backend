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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDeathPlace() {
        return deathPlace;
    }

    public void setDeathPlace(String deathPlace) {
        this.deathPlace = deathPlace;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getDeceasedName() {
        return deceasedName;
    }

    public void setDeceasedName(String deceasedName) {
        this.deceasedName = deceasedName;
    }

    public String getDeceasedAge() {
        return deceasedAge;
    }

    public void setDeceasedAge(String deceasedAge) {
        this.deceasedAge = deceasedAge;
    }

    public String getDeceasedGender() {
        return deceasedGender;
    }

    public void setDeceasedGender(String deceasedGender) {
        this.deceasedGender = deceasedGender;
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getDeclarationPlace() {
        return declarationPlace;
    }

    public void setDeclarationPlace(String declarationPlace) {
        this.declarationPlace = declarationPlace;
    }

    public String getInformantName() {
        return informantName;
    }

    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }

    public String getInformantRelationship() {
        return informantRelationship;
    }

    public void setInformantRelationship(String informantRelationship) {
        this.informantRelationship = informantRelationship;
    }

    public String getFuneralPlace() {
        return funeralPlace;
    }

    public void setFuneralPlace(String funeralPlace) {
        this.funeralPlace = funeralPlace;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }
}
