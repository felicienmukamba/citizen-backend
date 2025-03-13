package com.soside.backend.repositories;

import com.soside.backend.models.BiometricData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometricDataRepository extends JpaRepository<BiometricData, Long> {

    BiometricData findByPersonNationalityID(String nationalityID);
}
