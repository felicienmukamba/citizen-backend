package com.soside.backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.soside.backend.enums.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;
    private String maidenName;
    private LocalDate birthDate;
    private String birthPlace;

    @Column(nullable = false, unique = true)
    private String nationalityID;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private EthnicGroup ethnicGroup;

    private String birthCertificateNumber;
    private String placeOfBirth;
    private String fathersName;
    private String mothersName;
    private String fathersProfession;
    private String mothersProfession;
    private String currentAddress;
    private String phoneNumber;
    private String emailAddress;
    private String emergencyContactName;
    private String emergencyContactPhone;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @ElementCollection(targetClass = PersonAllergies.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "person_allergies", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "allergy")
    private List<PersonAllergies> allergies;

    private String disabilities;
    private String educationLevel;
    private String profession;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private String occupation;
    private String religion;
    private String voterStatus;
    private String taxIdentificationNumber;
    private String socialSecurityNumber;
    private String drivingLicenseNumber;
    private String passportNumber;

    // 📸 Images (paths to server-side stored files)
    private String passportPhoto1Path;
    private String passportPhoto2Path;
    private String passportPhoto3Path;

    // 👇 Relations
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BirthRecord birthRecord;

    @OneToMany(mappedBy = "partner1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("person-marriages1")
    private List<MarriageRecord> marriagesAsPartner1;

    @OneToMany(mappedBy = "partner2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("person-marriages2")
    private List<MarriageRecord> marriagesAsPartner2;

    @OneToMany(mappedBy = "witness1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("person-witness1")
    private List<MarriageRecord> marriagesAsWitness1;

    @OneToMany(mappedBy = "witness2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("person-witness2")
    private List<MarriageRecord> marriagesAsWitness2;

    @OneToMany(mappedBy = "witness3", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("person-witness3")
    private List<MarriageRecord> marriagesAsWitness3;

    @OneToMany(mappedBy = "officiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("person-officiant")
    private List<MarriageRecord> marriagesAsOfficiant;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DeathRecord deathRecord;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HealthRecord> healthRecords;

    @OneToMany(mappedBy = "person")
    private List<CriminalRecord> criminalRecord;

    @OneToMany(mappedBy = "plaintiff")
    private List<Complaint> filedComplaints;

    @OneToMany(mappedBy = "accused")
    private List<Complaint> receivedComplaints;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BiometricData> biometricData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public EthnicGroup getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(EthnicGroup ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public String getBirthCertificateNumber() {
        return birthCertificateNumber;
    }

    public void setBirthCertificateNumber(String birthCertificateNumber) {
        this.birthCertificateNumber = birthCertificateNumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getFathersProfession() {
        return fathersProfession;
    }

    public void setFathersProfession(String fathersProfession) {
        this.fathersProfession = fathersProfession;
    }

    public String getMothersProfession() {
        return mothersProfession;
    }

    public void setMothersProfession(String mothersProfession) {
        this.mothersProfession = mothersProfession;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public List<PersonAllergies> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<PersonAllergies> allergies) {
        this.allergies = allergies;
    }

    public String getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(String disabilities) {
        this.disabilities = disabilities;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getVoterStatus() {
        return voterStatus;
    }

    public void setVoterStatus(String voterStatus) {
        this.voterStatus = voterStatus;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportPhoto1Path() {
        return passportPhoto1Path;
    }

    public void setPassportPhoto1Path(String passportPhoto1Path) {
        this.passportPhoto1Path = passportPhoto1Path;
    }

    public String getPassportPhoto2Path() {
        return passportPhoto2Path;
    }

    public void setPassportPhoto2Path(String passportPhoto2Path) {
        this.passportPhoto2Path = passportPhoto2Path;
    }

    public String getPassportPhoto3Path() {
        return passportPhoto3Path;
    }

    public void setPassportPhoto3Path(String passportPhoto3Path) {
        this.passportPhoto3Path = passportPhoto3Path;
    }

    public BirthRecord getBirthRecord() {
        return birthRecord;
    }

    public void setBirthRecord(BirthRecord birthRecord) {
        this.birthRecord = birthRecord;
    }

    public List<MarriageRecord> getMarriagesAsPartner1() {
        return marriagesAsPartner1;
    }

    public void setMarriagesAsPartner1(List<MarriageRecord> marriagesAsPartner1) {
        this.marriagesAsPartner1 = marriagesAsPartner1;
    }

    public List<MarriageRecord> getMarriagesAsPartner2() {
        return marriagesAsPartner2;
    }

    public void setMarriagesAsPartner2(List<MarriageRecord> marriagesAsPartner2) {
        this.marriagesAsPartner2 = marriagesAsPartner2;
    }

    public List<MarriageRecord> getMarriagesAsWitness1() {
        return marriagesAsWitness1;
    }

    public void setMarriagesAsWitness1(List<MarriageRecord> marriagesAsWitness1) {
        this.marriagesAsWitness1 = marriagesAsWitness1;
    }

    public List<MarriageRecord> getMarriagesAsWitness2() {
        return marriagesAsWitness2;
    }

    public void setMarriagesAsWitness2(List<MarriageRecord> marriagesAsWitness2) {
        this.marriagesAsWitness2 = marriagesAsWitness2;
    }

    public List<MarriageRecord> getMarriagesAsWitness3() {
        return marriagesAsWitness3;
    }

    public void setMarriagesAsWitness3(List<MarriageRecord> marriagesAsWitness3) {
        this.marriagesAsWitness3 = marriagesAsWitness3;
    }

    public List<MarriageRecord> getMarriagesAsOfficiant() {
        return marriagesAsOfficiant;
    }

    public void setMarriagesAsOfficiant(List<MarriageRecord> marriagesAsOfficiant) {
        this.marriagesAsOfficiant = marriagesAsOfficiant;
    }

    public DeathRecord getDeathRecord() {
        return deathRecord;
    }

    public void setDeathRecord(DeathRecord deathRecord) {
        this.deathRecord = deathRecord;
    }

    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }

    public void setHealthRecords(List<HealthRecord> healthRecords) {
        this.healthRecords = healthRecords;
    }

    public List<CriminalRecord> getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(List<CriminalRecord> criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public List<Complaint> getFiledComplaints() {
        return filedComplaints;
    }

    public void setFiledComplaints(List<Complaint> filedComplaints) {
        this.filedComplaints = filedComplaints;
    }

    public List<Complaint> getReceivedComplaints() {
        return receivedComplaints;
    }

    public void setReceivedComplaints(List<Complaint> receivedComplaints) {
        this.receivedComplaints = receivedComplaints;
    }

    public List<BiometricData> getBiometricData() {
        return biometricData;
    }

    public void setBiometricData(List<BiometricData> biometricData) {
        this.biometricData = biometricData;
    }
}
