package com.soside.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "biometric_data")
public class BiometricData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Images de reconnaissance faciale (minimum 3)
    @ElementCollection
    @CollectionTable(
            name = "facial_recognition_images",
            joinColumns = @JoinColumn(name = "biometric_data_id")
    )
    @Column(name = "image_path")
    @Lob
    private List<String> facialRecognitionImages;

    // Empreintes digitales (jusqu’à 10 doigts)
    @ElementCollection
    @CollectionTable(
            name = "fingerprint_data",
            joinColumns = @JoinColumn(name = "biometric_data_id")
    )
    @Column(name = "fingerprint")
    @Lob
    private List<String> fingerprints;

    // Scans de l’iris
    @Lob
    private String leftEyeScan;

    @Lob
    private String rightEyeScan;

    // Référence à la personne concernée
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    @NotNull
    private Person person;

    // Utilisateur qui a saisi les données
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    // --- Constructors ---

    public BiometricData() {
    }

    public BiometricData(Long id, List<String> facialRecognitionImages, List<String> fingerprints,
                         String leftEyeScan, String rightEyeScan, Person person, User user) {
        this.id = id;
        this.facialRecognitionImages = facialRecognitionImages;
        this.fingerprints = fingerprints;
        this.leftEyeScan = leftEyeScan;
        this.rightEyeScan = rightEyeScan;
        this.person = person;
        this.user = user;
    }

    // --- Getters and Setters ---

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
