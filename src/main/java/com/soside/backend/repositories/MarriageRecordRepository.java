package com.soside.backend.repositories;

import com.soside.backend.models.MarriageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarriageRecordRepository extends JpaRepository<MarriageRecord, Long> {
    // You can define custom queries here if necessary
}
