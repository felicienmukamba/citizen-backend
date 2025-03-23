package com.soside.backend.services.carehistory;

import com.soside.backend.exceptions.ResourceNotFoundException;
import com.soside.backend.models.CareHistory;
import com.soside.backend.repositories.CareHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareHistoryServiceImpl implements ICareHistoryService {

    @Autowired
    private CareHistoryRepository careHistoryRepository;

    @Override
    public CareHistory createCareHistory(CareHistory careHistory) {
        return careHistoryRepository.save(careHistory);
    }

    @Override
    public CareHistory updateCareHistory(Long id, CareHistory careHistory) {
        CareHistory existingCareHistory = careHistoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CareHistory", "id", id));
        existingCareHistory.setCareType(careHistory.getCareType());
        existingCareHistory.setCareDate(careHistory.getCareDate());
        existingCareHistory.setCareDescription(careHistory.getCareDescription());
        existingCareHistory.setDoctorName(careHistory.getDoctorName());
        existingCareHistory.setMedicalFacility(careHistory.getMedicalFacility());
        return careHistoryRepository.save(existingCareHistory);
    }

    @Override
    public void deleteCareHistory(Long id) {
        CareHistory existingCareHistory = careHistoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CareHistory", "id", id));
        careHistoryRepository.delete(existingCareHistory);
    }

    @Override
    public CareHistory getCareHistoryById(Long id) {
        return careHistoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CareHistory", "id", id));
    }

    @Override
    public List<CareHistory> getAllCareHistories() {
        return careHistoryRepository.findAll();
    }
}