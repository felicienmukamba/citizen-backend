package com.soside.backend.services.healthrecord;

import com.soside.backend.models.HealthRecord;

import java.util.List;

public interface IHealthRecordService {
    HealthRecord createHealthRecord(HealthRecord healthRecord);
    HealthRecord updateHealthRecord(Long id, HealthRecord healthRecord);
    void deleteHealthRecord(Long id);
    HealthRecord getHealthRecordById(Long id);
    List<HealthRecord> getAllHealthRecords();
}