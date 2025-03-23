package com.soside.backend.services.birthrecord;

import com.soside.backend.models.BirthRecord;

import java.util.List;

public interface BirthRecordService {
    BirthRecord createBirthRecord(BirthRecord birthRecord);
    BirthRecord updateBirthRecord(Long id, BirthRecord birthRecord);
    void deleteBirthRecord(Long id);
    BirthRecord getBirthRecordById(Long id);
    List<BirthRecord> getAllBirthRecords();
}