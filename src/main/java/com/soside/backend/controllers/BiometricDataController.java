package com.soside.backend.controllers;
import com.soside.backend.models.BiometricData;
import com.soside.backend.services.biometricData.BiometricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("biometric-data")
public class BiometricDataController {

     @Autowired
     private BiometricDataService biometricDataService;

     // Créer une nouvelle donnée biométrique
     @PostMapping
     public ResponseEntity<BiometricData> createBiometricData(@RequestBody BiometricData biometricData) {
         BiometricData savedData = biometricDataService.addBiometricData(biometricData);
         return ResponseEntity.ok(savedData);
     }

     // Récupérer par ID
     @GetMapping("/{id}")
     public ResponseEntity<BiometricData> getBiometricDataById(@PathVariable Long id) {
         BiometricData biometricData = biometricDataService.getBiometricDataById(id);
         return ResponseEntity.ok(biometricData);
     }

     // Récupérer par Person ID
     @GetMapping("/person/{personId}")
     public ResponseEntity<BiometricData> getBiometricDataByPersonId(@PathVariable String nationalityI) {
         BiometricData biometricData = biometricDataService.getBiometricDataByPersonNationalityID(nationalityI);
         return ResponseEntity.ok(biometricData);
     }

     // Récupérer toutes les données biométriques
     @GetMapping
     public ResponseEntity<List<BiometricData>> getAllBiometricData() {
         List<BiometricData> biometricDataList = biometricDataService.getAllBiometricData();
         return ResponseEntity.ok(biometricDataList);
     }

     // Supprimer une donnée biométrique par ID
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteBiometricData(@PathVariable Long id) {
         biometricDataService.deleteBiometricData(id);
         return ResponseEntity.noContent().build();
     }
}