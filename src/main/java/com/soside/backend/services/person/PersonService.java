package com.soside.backend.services.person;
import com.soside.backend.exceptions.PersonNotFoundException;
import com.soside.backend.models.Person;
import com.soside.backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person addPerson(Person person) {
        this.personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> addAllPersons(List<Person> persons) {
        return this.personRepository.saveAll(persons);
    }

    @Override
    public void editPerson(Person updatedPerson, String nationalityID) {
        Person existingPerson = this.personRepository.findByNationalityID(nationalityID)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));

        existingPerson.setLastName(updatedPerson.getLastName());
        existingPerson.setFirstName(updatedPerson.getFirstName());
        existingPerson.setMaidenName(updatedPerson.getMaidenName());
        existingPerson.setBirthDate(updatedPerson.getBirthDate());
        existingPerson.setBirthPlace(updatedPerson.getBirthPlace());
        existingPerson.setNationality(updatedPerson.getNationality());
        existingPerson.setGender(updatedPerson.getGender());
        existingPerson.setEthnicGroup(updatedPerson.getEthnicGroup());
        existingPerson.setBirthCertificateNumber(updatedPerson.getBirthCertificateNumber());
        existingPerson.setPlaceOfBirth(updatedPerson.getPlaceOfBirth());
        existingPerson.setFathersName(updatedPerson.getFathersName());
        existingPerson.setMothersName(updatedPerson.getMothersName());
        existingPerson.setFathersProfession(updatedPerson.getFathersProfession());
        existingPerson.setMothersProfession(updatedPerson.getMothersProfession());
        existingPerson.setCurrentAddress(updatedPerson.getCurrentAddress());
        existingPerson.setPhoneNumber(updatedPerson.getPhoneNumber());
        existingPerson.setEmailAddress(updatedPerson.getEmailAddress());
        existingPerson.setEmergencyContactName(updatedPerson.getEmergencyContactName());
        existingPerson.setEmergencyContactPhone(updatedPerson.getEmergencyContactPhone());
        existingPerson.setBloodType(updatedPerson.getBloodType());
        existingPerson.setAllergies(updatedPerson.getAllergies());
        existingPerson.setDisabilities(updatedPerson.getDisabilities());
        existingPerson.setEducationLevel(updatedPerson.getEducationLevel());
        existingPerson.setProfession(updatedPerson.getProfession());
        existingPerson.setMaritalStatus(updatedPerson.getMaritalStatus());
        existingPerson.setOccupation(updatedPerson.getOccupation());
        existingPerson.setReligion(updatedPerson.getReligion());
        existingPerson.setVoterStatus(updatedPerson.getVoterStatus());
        existingPerson.setTaxIdentificationNumber(updatedPerson.getTaxIdentificationNumber());
        existingPerson.setSocialSecurityNumber(updatedPerson.getSocialSecurityNumber());
        existingPerson.setDrivingLicenseNumber(updatedPerson.getDrivingLicenseNumber());
        existingPerson.setPassportNumber(updatedPerson.getPassportNumber());

        this.personRepository.save(existingPerson);
    }


    @Override
    public void deletePerson(String nationalityID) {
        this.personRepository.findByNationalityID(nationalityID)
                .ifPresentOrElse(personRepository::delete, () -> {
                    throw new PersonNotFoundException("Person not found");
                });
    }

    @Override
    public Person getPersonByNationalityID(String nationalityID) {
        return this.personRepository.findByNationalityID(nationalityID)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    @Override
    public Person getPersonByPhoneNumber(String phoneNumber) {
        return this.personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }


    @Override
    public Person getPersonByEmailAddress(String emailAddress) {
        return this.personRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    @Override
    public List<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    @Override
    public List<Person> getPersonsByGender(String gender) {
        return this.personRepository.findByGender(gender);
    }

    @Override
    public List<Person> getPersonsByMaritalStatus(String maritalStatus) {
        return this.personRepository.findByMaritalStatus(maritalStatus);
    }

    @Override
    public List<Person> getPersonsByBloodType(String bloodType) {
        return this.personRepository.findByBloodType(bloodType);
    }

    public void savePassportPhotos(String nationalityID, MultipartFile[] files) {
        String uploadDir = "uploads/persons/" + nationalityID;
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            Path filePath = Paths.get(uploadDir, "passport" + (i + 1) + ".jpg");
            try {
                Files.write(filePath, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Échec de l’enregistrement des images.", e);
            }
        }
    }

    public Resource loadPassportPhoto(String nationalityID, int index) {
        String uploadDir = "uploads/persons/" + nationalityID;
        Path filePath = Paths.get(uploadDir, "passport" + index + ".jpg");
        if (!Files.exists(filePath)) {
            throw new RuntimeException("Image introuvable");
        }
        return new FileSystemResource(filePath.toFile());
    }
}