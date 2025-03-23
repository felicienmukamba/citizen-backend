package com.soside.backend.repositories;

import com.soside.backend.models.CareHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareHistoryRepository extends JpaRepository<CareHistory, Long> {
}