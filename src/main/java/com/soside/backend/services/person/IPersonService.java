package com.soside.backend.services.person;
import com.soside.backend.models.Person;

import java.util.List;

/**
 * Interface representing the contract for person-related operations.
 */
public interface IPersonService {

    /**
     * Adds a new person to the repository.
     *
     * @param person the person to be added
     * @return the added person
     */
    Person addPerson(Person person);

    /**
     * AddAll a new persons to the repository.
     *
     * @param persons the persons to be added
     * @return the added person list
     */
    List<Person> addAllPersons(List<Person> persons);

    /**
     * Edits an existing person identified by nationalityID.
     *
     * @param person the updated person details
     * @param nationalityID the nationality ID of the person to be edited
     */
    void editPerson(Person person, String nationalityID);

    /**
     * Deletes a person identified by nationalityID.
     *
     * @param nationalityID the nationality ID of the person to be deleted
     */
    void deletePerson(String nationalityID);

    /**
     * Retrieves a person identified by nationalityID.
     *
     * @param nationalityID the nationality ID of the person to be retrieved
     * @return the person with the specified nationality ID
     */
    Person getPersonByNationalityID(String nationalityID);

    /**
     * Retrieves a person identified by phone number.
     *
     * @param phoneNumber the phone number of the person to be retrieved
     * @return the person with the specified phone number
     */
    Person getPersonByPhoneNumber(String phoneNumber);

    /**
     * Retrieves a person identified by email address.
     *
     * @param emailAddress the email address of the person to be retrieved
     * @return the person with the specified email address
     */
    Person getPersonByEmailAddress(String emailAddress);

    /**
     * Retrieves all persons.
     *
     * @return a list of all persons
     */
    List<Person> getAllPersons();

    /**
     * Retrieves persons by gender.
     *
     * @param gender the gender of the persons to be retrieved
     * @return a list of persons with the specified gender
     */
    List<Person> getPersonsByGender(String gender);

    /**
     * Retrieves persons by marital status.
     *
     * @param maritalStatus the marital status of the persons to be retrieved
     * @return a list of persons with the specified marital status
     */
    List<Person> getPersonsByMaritalStatus(String maritalStatus);


    /**
     * Retrieves persons by blood type.
     *
     * @param bloodType the blood type of the persons to be retrieved
     * @return a list of persons with the specified blood type
     */
    List<Person> getPersonsByBloodType(String bloodType);
}
