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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/biometric")
public class BiometricDataController {

    private final BiometricDataRepository biometricDataRepository;
    private final PersonService personService;
    private final RestTemplate restTemplate;
    private final String pythonApiUrl = "http://localhost:5000"; // URL de l'API Python

    @Autowired
    public BiometricDataController(BiometricDataRepository biometricDataRepository, PersonService personService, RestTemplate restTemplate) {
        this.biometricDataRepository = biometricDataRepository;
        this.restTemplate = restTemplate;
        this.personService = personService;
    }

    @PostMapping("/recognize-face/{nationalityID}")
    public ResponseEntity<Map<String, Boolean>> recognizeFace(@PathVariable String nationalityID, @RequestParam("image") MultipartFile imageFile) {
        Optional<Person> personOptional = Optional.ofNullable(personService.getPersonByNationalityID(nationalityID));
        if (personOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", false)); // Personne non trouvée
        }
        Person person = personOptional.get();
        BiometricData biometricData = biometricDataRepository.findByPerson(person);

        if (biometricData == null || biometricData.getFacialRecognitionImages() == null || biometricData.getFacialRecognitionImages().isEmpty()) {
            return ResponseEntity.ok(Map.of("match", false)); // Pas d'images faciales enregistrées pour cette personne
        }

        List<String> knownImagePaths = biometricData.getFacialRecognitionImages();
        List<String> knownEncodingsBase64 = new ArrayList<>();

        // Simuler l'encodage des images connues (dans une application réelle, vous pourriez pré-calculer et stocker les encodages)
        for (String imagePath : knownImagePaths) {
            try {
                Path path = Path.of(imagePath);
                byte[] imageBytes = Files.readAllBytes(path);
                // Ici, vous devriez appeler votre script Python pour encoder cette image
                // Pour cet exemple, nous allons simuler un encodage en base64
                String encodedString = Base64.getEncoder().encodeToString(imageBytes);
                knownEncodingsBase64.add(encodedString);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'erreur si une image n'est pas trouvée
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        org.springframework.util.LinkedMultiValueMap<String, Object> body = new org.springframework.util.LinkedMultiValueMap<>();
        try {
            body.add("image", new org.springframework.core.io.ByteArrayResource(imageFile.getBytes()) {
                @Override
                public String getFilename() {
                    return imageFile.getOriginalFilename();
                }
            });
            for (String encoding : knownEncodingsBase64) {
                body.add("known_encodings", encoding);
            }

            HttpEntity<org.springframework.util.MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl + "/recognize", requestEntity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return ResponseEntity.ok(Map.of("match", (Boolean) response.getBody().get("match")));
            } else {
                System.err.println("Erreur lors de l'appel à l'API Python: " + response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).body(Map.of("match", false));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", false));
        }
    }
}