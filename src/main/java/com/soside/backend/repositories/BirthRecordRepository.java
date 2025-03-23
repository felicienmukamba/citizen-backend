package com.soside.backend.repositories;

import com.soside.backend.models.BirthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthRecordRepository extends JpaRepository<BirthRecord, Long> {
}