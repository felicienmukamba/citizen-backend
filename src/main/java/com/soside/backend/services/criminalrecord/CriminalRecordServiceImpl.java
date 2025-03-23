package com.soside.backend.services.criminalrecord;

import com.soside.backend.exceptions.ResourceNotFoundException;
import com.soside.backend.models.CriminalRecord;
import com.soside.backend.repositories.CriminalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalRecordServiceImpl implements ICriminalRecordService {

    @Autowired
    private CriminalRecordRepository criminalRecordRepository;

    @Override
    public CriminalRecord createCriminalRecord(CriminalRecord criminalRecord) {
        return criminalRecordRepository.save(criminalRecord);
    }

    @Override
    public CriminalRecord updateCriminalRecord(Long id, CriminalRecord criminalRecord) {
        CriminalRecord existingCriminalRecord = criminalRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CriminalRecord", "id", id));
        existingCriminalRecord.setConvictionDate(criminalRecord.getConvictionDate());
        existingCriminalRecord.setOffenseDescription(criminalRecord.getOffenseDescription());
        existingCriminalRecord.setCourtName(criminalRecord.getCourtName());
        existingCriminalRecord.setJudgeName(criminalRecord.getJudgeName());
        existingCriminalRecord.setSentence(criminalRecord.getSentence());
        existingCriminalRecord.setSentenceType(criminalRecord.getSentenceType());
        existingCriminalRecord.setSentenceDuration(criminalRecord.getSentenceDuration());
        existingCriminalRecord.setReleaseDate(criminalRecord.getReleaseDate());
        existingCriminalRecord.setParoleStatus(criminalRecord.getParoleStatus());
        existingCriminalRecord.setRehabilitationStatus(criminalRecord.getRehabilitationStatus());
        existingCriminalRecord.setCaseNumber(criminalRecord.getCaseNumber());
        existingCriminalRecord.setProsecutorName(criminalRecord.getProsecutorName());
        existingCriminalRecord.setDefenseAttorneyName(criminalRecord.getDefenseAttorneyName());
        existingCriminalRecord.setAppealStatus(criminalRecord.getAppealStatus());
        existingCriminalRecord.setNotes(criminalRecord.getNotes());
        existingCriminalRecord.setAuthorizedBy(criminalRecord.getAuthorizedBy());
        existingCriminalRecord.setConsultationDate(criminalRecord.getConsultationDate());
        existingCriminalRecord.setConsultationReason(criminalRecord.getConsultationReason());
        return criminalRecordRepository.save(existingCriminalRecord);
    }

    @Override
    public void deleteCriminalRecord(Long id) {
        CriminalRecord existingCriminalRecord = criminalRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CriminalRecord", "id", id));
        criminalRecordRepository.delete(existingCriminalRecord);
    }

    @Override
    public CriminalRecord getCriminalRecordById(Long id) {
        return criminalRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CriminalRecord", "id", id));
    }

    @Override
    public List<CriminalRecord> getAllCriminalRecords() {
        return criminalRecordRepository.findAll();
    }
}