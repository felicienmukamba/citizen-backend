package com.soside.backend.controllers;

import com.soside.backend.models.BiometricData;
import com.soside.backend.models.Person;
import com.soside.backend.repositories.BiometricDataRepository;
import com.soside.backend.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/biometric")
public class BiometricDataController {

    private final BiometricDataRepository biometricDataRepository;
    private final PersonService personService;
    private final RestTemplate restTemplate;

    @Autowired
    public BiometricDataController(BiometricDataRepository biometricDataRepository, PersonService personService, RestTemplate restTemplate) {
        this.biometricDataRepository = biometricDataRepository;
        this.personService = personService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/face-recognition")
    public ResponseEntity<Person> recognizeFace(@RequestParam("image") MultipartFile imageFile) {
        try {
            byte[] imageData = imageFile.getBytes();
            String facialRecognitionApiUrl = "http://facial-recognition-api/recognize";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpEntity<byte[]> requestEntity = new HttpEntity<>(imageData, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(facialRecognitionApiUrl, requestEntity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().containsKey("personId")) {
                String personId = String.valueOf(response.getBody().get("personId"));
                Person person = personService.getPersonByNationalityID(personId);
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/iris-recognition")
    public ResponseEntity<Person> recognizeIris(@RequestParam("irisData") String irisData, @RequestParam("isLeft") boolean isLeft) {
        try {
            byte[] irisBytes = irisData.getBytes();
            String irisRecognitionApiUrl = "http://iris-recognition-api/recognize";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpEntity<byte[]> requestEntity = new HttpEntity<>(irisBytes, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(irisRecognitionApiUrl, requestEntity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().containsKey("personId")) {
                String personId = String.valueOf(response.getBody().get("personId"));
                Person person = personService.getPersonByNationalityID(personId);
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/fingerprint-recognition")
    public ResponseEntity<Person> recognizeFingerprint(@RequestParam("fingerprintData") String fingerprintData) {
        try {
            byte[] fingerprintBytes = fingerprintData.getBytes();
            String fingerprintRecognitionApiUrl = "http://fingerprint-recognition-api/recognize";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpEntity<byte[]> requestEntity = new HttpEntity<>(fingerprintBytes, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(fingerprintRecognitionApiUrl, requestEntity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().containsKey("personId")) {
                String personId = String.valueOf(response.getBody().get("personId"));
                Person person = personService.getPersonByNationalityID(personId);
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/add-biometric-data/{personId}")
    public ResponseEntity<BiometricData> addBiometricData(
            @PathVariable String personId,
            @RequestParam(value = "facialImages", required = false) MultipartFile[] facialImages,
            @RequestParam(value = "leftIris", required = false) MultipartFile leftIris,
            @RequestParam(value = "rightIris", required = false) MultipartFile rightIris,
            @RequestParam(value = "fingerprints", required = false) MultipartFile[] fingerprints) {
        try {
            Person person = personService.getPersonByNationalityID(personId);
            BiometricData biometricData = new BiometricData();
            biometricData.setPerson(person);

            if (facialImages != null && facialImages.length > 0) {
                List<String> facialDataList = new ArrayList<>();
                for (MultipartFile imageFile : facialImages) {
                    facialDataList.add(Base64.getEncoder().encodeToString(imageFile.getBytes()));
                }
                biometricData.setFacialRecognitionImages(facialDataList);
            }

            if (leftIris != null) {
                biometricData.setLeftEyeScan(Base64.getEncoder().encodeToString(leftIris.getBytes()));
            }

            if (rightIris != null) {
                biometricData.setRightEyeScan(Base64.getEncoder().encodeToString(rightIris.getBytes()));
            }

            if (fingerprints != null && fingerprints.length > 0) {
                List<String> fingerprintDataList = new ArrayList<>();
                for (MultipartFile fingerprintFile : fingerprints) {
                    fingerprintDataList.add(Base64.getEncoder().encodeToString(fingerprintFile.getBytes()));
                }
                biometricData.setFingerprints(fingerprintDataList);
            }

            BiometricData savedBiometricData = biometricDataRepository.save(biometricData);
            return ResponseEntity.ok(savedBiometricData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/person/{nationalityID}")
    public ResponseEntity<BiometricData> getBiometricByPerson(@PathVariable String nationalityID) {
        try {
            Person person = personService.getPersonByNationalityID(nationalityID);
            BiometricData biometricData = biometricDataRepository.findByPerson(person);
            if (biometricData != null) {
                return ResponseEntity.ok(biometricData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}