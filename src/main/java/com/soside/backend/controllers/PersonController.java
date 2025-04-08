package com.soside.backend.controllers;

import com.soside.backend.models.Person;
import com.soside.backend.services.person.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person savedPerson = personService.addPerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PostMapping("all")
    public ResponseEntity<List<Person>> addPerson(@RequestBody List<Person> persons) {
        List<Person> savedPersons = personService.addAllPersons(persons);
        return new ResponseEntity<>(savedPersons, HttpStatus.CREATED);
    }

    @PutMapping("/{nationalityID}")
    public ResponseEntity<Void> editPerson(@RequestBody Person person, @PathVariable String nationalityID) {
        personService.editPerson(person, nationalityID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{nationalityID}")
    public ResponseEntity<Void> deletePerson(@PathVariable String nationalityID) {
        personService.deletePerson(nationalityID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{nationalityID}")
    public ResponseEntity<Person> getPersonByNationalityID(@PathVariable String nationalityID) {
        Person person = personService.getPersonByNationalityID(nationalityID);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<Person> getPersonByPhoneNumber(@PathVariable String phoneNumber) {
        Person person = personService.getPersonByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/email/{emailAddress}")
    public ResponseEntity<Person> getPersonByEmailAddress(@PathVariable String emailAddress) {
        Person person = personService.getPersonByEmailAddress(emailAddress);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = this.personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Person>> getPersonsByGender(@PathVariable String gender) {
        List<Person> persons = personService.getPersonsByGender(gender);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/marital-status/{maritalStatus}")
    public ResponseEntity<List<Person>> getPersonsByMaritalStatus(@PathVariable String maritalStatus) {
        List<Person> persons = personService.getPersonsByMaritalStatus(maritalStatus);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/blood-type/{bloodType}")
    public ResponseEntity<List<Person>> getPersonsByBloodType(@PathVariable String bloodType) {
        List<Person> persons = personService.getPersonsByBloodType(bloodType);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    // 🔽 Upload jusqu’à 3 photos passeport
    @PostMapping(value = "/{nationalityID}/upload-passport", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPassportPhotos(
            @PathVariable String nationalityID,
            @RequestParam("files") MultipartFile[] files) {
        if (files.length > 3) {
            return ResponseEntity.badRequest().body("Maximum 3 images autorisées.");
        }
        personService.savePassportPhotos(nationalityID, files);
        return ResponseEntity.ok("Images enregistrées avec succès.");
    }

    // 🔽 Récupérer une photo spécifique (1, 2 ou 3)
    @GetMapping("/{nationalityID}/passport-photo/{index}")
    public ResponseEntity<Resource> getPassportPhoto(
            @PathVariable String nationalityID,
            @PathVariable int index,
            HttpServletResponse response) throws IOException {
        Resource file = personService.loadPassportPhoto(nationalityID, index);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
}
