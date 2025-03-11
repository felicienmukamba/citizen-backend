package com.soside.backend.models;
import com.soside.backend.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private PersonAllergies allergies;

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

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BirthRecord birthRecord;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MarriageRecord> marriageRecord;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DeathRecord deathRecord;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HealthRecord> healthRecords;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CriminalRecord> criminalRecord;

    @OneToMany(mappedBy = "plaintiff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Complaint> filedComplaints;

    @OneToMany(mappedBy = "accused", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Complaint> receivedComplaints;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BiometricData> biometricData;


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

    public PersonAllergies getAllergies() {
        return allergies;
    }

    public void setAllergies(PersonAllergies allergies) {
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

    public BirthRecord getBirthRecord() {
        return birthRecord;
    }

    public void setBirthRecord(BirthRecord birthRecord) {
        this.birthRecord = birthRecord;
    }

    public List<MarriageRecord> getMarriageRecord() {
        return marriageRecord;
    }

    public void setMarriageRecord(List<MarriageRecord> marriageRecord) {
        this.marriageRecord = marriageRecord;
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
