package com.soside.backend.services.deathrecord;

import com.soside.backend.models.DeathRecord;

import java.util.List;

public interface DeathRecordService {
    DeathRecord createDeathRecord(DeathRecord deathRecord);
    DeathRecord updateDeathRecord(Long id, DeathRecord deathRecord);
    void deleteDeathRecord(Long id);
    DeathRecord getDeathRecordById(Long id);
    List<DeathRecord> getAllDeathRecords();
}