package com.soside.backend.repositories;

import com.soside.backend.models.DeathRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeathRecordRepository extends JpaRepository<DeathRecord, Long> {
}
