package com.soside.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "biometric_data")
public class BiometricData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reconnaissance faciale (trois images minimum)
    @ElementCollection
    @CollectionTable(name = "facial_recognition_images", joinColumns = @JoinColumn(name = "biometric_data_id"))
    @Column(name = "image_path")
    private List<String> facialRecognitionImages;

    // Empreintes digitales (10 doigts)
    @ElementCollection
    @CollectionTable(name = "fingerprint_data", joinColumns = @JoinColumn(name = "biometric_data_id"))
    @Column(name = "fingerprint")
    private List<String> fingerprints;

    // Scans des yeux
    private String leftEyeScan;
    private String rightEyeScan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getFacialRecognitionImages() {
        return facialRecognitionImages;
    }

    public void setFacialRecognitionImages(List<String> facialRecognitionImages) {
        this.facialRecognitionImages = facialRecognitionImages;
    }

    public List<String> getFingerprints() {
        return fingerprints;
    }

    public void setFingerprints(List<String> fingerprints) {
        this.fingerprints = fingerprints;
    }

    public String getLeftEyeScan() {
        return leftEyeScan;
    }

    public void setLeftEyeScan(String leftEyeScan) {
        this.leftEyeScan = leftEyeScan;
    }

    public String getRightEyeScan() {
        return rightEyeScan;
    }

    public void setRightEyeScan(String rightEyeScan) {
        this.rightEyeScan = rightEyeScan;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
