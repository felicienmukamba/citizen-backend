package com.soside.backend.services.marriageRecord;

import com.soside.backend.models.MarriageRecord;
import com.soside.backend.repositories.MarriageRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarriageRecordService implements IMarriageRecordService {

    private final MarriageRecordRepository repository;

    public MarriageRecordService(MarriageRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public MarriageRecord saveMarriageRecord(MarriageRecord record) {
        return repository.save(record);
    }

    @Override
    public List<MarriageRecord> getAllMarriageRecords() {
        return repository.findAll();
    }

    @Override
    public MarriageRecord getMarriageRecordById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteMarriageRecord(Long id) {
        repository.deleteById(id);
    }
}
