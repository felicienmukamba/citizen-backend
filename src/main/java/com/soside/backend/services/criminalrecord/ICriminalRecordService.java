package com.soside.backend.services.criminalrecord;

import com.soside.backend.models.CriminalRecord;

import java.util.List;

public interface ICriminalRecordService {
    CriminalRecord createCriminalRecord(CriminalRecord criminalRecord);
    CriminalRecord updateCriminalRecord(Long id, CriminalRecord criminalRecord);
    void deleteCriminalRecord(Long id);
    CriminalRecord getCriminalRecordById(Long id);
    List<CriminalRecord> getAllCriminalRecords();
}