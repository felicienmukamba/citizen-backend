package com.soside.backend.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.soside.backend.enums.ContractType;
import com.soside.backend.enums.MarriageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "marriage_record")
public class MarriageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Partner 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner1_id", nullable = false)
    @JsonBackReference("person-marriages1")
    private Person partner1;

    // Partner 2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner2_id", nullable = false)
    @JsonBackReference("person-marriages2")
    private Person partner2;

    // Place of marriage
    @Column(nullable = false)
    private String marriagePlace;

    // Date of marriage
    @Column(nullable = false)
    private LocalDate marriageDate;

    // Officiant of the marriage
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officiant", nullable = false)
    @JsonBackReference("person-officiant")
    private Person officiant;

    // Witness 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "witness1_id", nullable = false)
    @JsonBackReference("person-witness1")
    private Person witness1;

    // Witness 2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "witness2_id", nullable = false)
    @JsonBackReference("person-witness2")
    private Person witness2;

    // Witness 3 (optional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("person-witness2")
    private Person witness3;

    // Type of marriage (enum)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MarriageType marriageType;

    // Type of contract (enum)
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    // Previous marriages (optional, can be a string or another linked entity)
    private String previousMarriages;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPartner1() {
        return partner1;
    }

    public void setPartner1(Person partner1) {
        this.partner1 = partner1;
    }

    public Person getPartner2() {
        return partner2;
    }

    public void setPartner2(Person partner2) {
        this.partner2 = partner2;
    }

    public String getMarriagePlace() {
        return marriagePlace;
    }

    public void setMarriagePlace(String marriagePlace) {
        this.marriagePlace = marriagePlace;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public Person getOfficiant() {
        return officiant;
    }

    public void setOfficiant(Person officiant) {
        this.officiant = officiant;
    }

    public Person getWitness1() {
        return witness1;
    }

    public void setWitness1(Person witness1) {
        this.witness1 = witness1;
    }

    public Person getWitness2() {
        return witness2;
    }

    public void setWitness2(Person witness2) {
        this.witness2 = witness2;
    }

    public Person getWitness3() {
        return witness3;
    }

    public void setWitness3(Person witness3) {
        this.witness3 = witness3;
    }

    public MarriageType getMarriageType() {
        return marriageType;
    }

    public void setMarriageType(MarriageType marriageType) {
        this.marriageType = marriageType;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getPreviousMarriages() {
        return previousMarriages;
    }

    public void setPreviousMarriages(String previousMarriages) {
        this.previousMarriages = previousMarriages;
    }
}

