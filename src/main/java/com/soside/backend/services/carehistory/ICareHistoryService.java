package com.soside.backend.services.carehistory;

import com.soside.backend.models.CareHistory;

import java.util.List;

public interface ICareHistoryService {
    CareHistory createCareHistory(CareHistory careHistory);
    CareHistory updateCareHistory(Long id, CareHistory careHistory);
    void deleteCareHistory(Long id);
    CareHistory getCareHistoryById(Long id);
    List<CareHistory> getAllCareHistories();
}