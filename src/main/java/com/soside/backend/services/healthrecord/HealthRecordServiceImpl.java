package com.soside.backend.services.healthrecord;

import com.soside.backend.exceptions.ResourceNotFoundException;
import com.soside.backend.models.HealthRecord;
import com.soside.backend.repositories.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthRecordServiceImpl implements IHealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    public HealthRecord createHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    @Override
    public HealthRecord updateHealthRecord(Long id, HealthRecord healthRecord) {
        HealthRecord existingHealthRecord = healthRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("HealthRecord", "id", id));
        existingHealthRecord.setRecordDate(healthRecord.getRecordDate());
        existingHealthRecord.setRecordType(healthRecord.getRecordType());
        existingHealthRecord.setDetails(healthRecord.getDetails());
        existingHealthRecord.setResult(healthRecord.getResult());
        existingHealthRecord.setDoctorName(healthRecord.getDoctorName());
        existingHealthRecord.setDoctorSpeciality(healthRecord.getDoctorSpeciality());
        existingHealthRecord.setMedicalFacility(healthRecord.getMedicalFacility());
        existingHealthRecord.setMedicalHistory(healthRecord.getMedicalHistory());
        existingHealthRecord.setCurrentMedication(healthRecord.getCurrentMedication());
        existingHealthRecord.setChronicConditions(healthRecord.getChronicConditions());
        existingHealthRecord.setAllergies(healthRecord.getAllergies());
        existingHealthRecord.setDisabilities(healthRecord.getDisabilities());
        existingHealthRecord.setVaccinations(healthRecord.getVaccinations());
        existingHealthRecord.setSurgeries(healthRecord.getSurgeries());
        existingHealthRecord.setHospitalizations(healthRecord.getHospitalizations());
        existingHealthRecord.setHealthInsuranceNumber(healthRecord.getHealthInsuranceNumber());
        return healthRecordRepository.save(existingHealthRecord);
    }

    @Override
    public void deleteHealthRecord(Long id) {
        HealthRecord existingHealthRecord = healthRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("HealthRecord", "id", id));
        healthRecordRepository.delete(existingHealthRecord);
    }

    @Override
    public HealthRecord getHealthRecordById(Long id) {
        return healthRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("HealthRecord", "id", id));
    }

    @Override
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }
}