package com.soside.backend.repositories;

import com.soside.backend.models.CriminalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRecordRepository extends JpaRepository<CriminalRecord, Long> {
}