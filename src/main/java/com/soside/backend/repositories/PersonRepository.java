package com.soside.backend.repositories;

import com.soside.backend.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByNationalityID(String nationalityID);
    Optional<Person> findByPhoneNumber(String phoneNumber);
    Optional<Person> findByEmailAddress(String emailAddress);
    List<Person> findByGender(String gender);
    List<Person> findByMaritalStatus(String maritalStatus);
    List<Person> findByBloodType(String bloodType);

}
