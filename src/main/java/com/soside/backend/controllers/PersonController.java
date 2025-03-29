package com.soside.backend.controllers;
import com.soside.backend.models.Person;
import com.soside.backend.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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
}
