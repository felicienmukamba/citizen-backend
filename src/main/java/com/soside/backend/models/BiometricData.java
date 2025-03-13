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
}
