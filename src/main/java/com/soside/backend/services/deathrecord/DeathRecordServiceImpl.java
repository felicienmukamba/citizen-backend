package com.soside.backend.services.deathrecord;

import com.soside.backend.models.DeathRecord;
import com.soside.backend.repositories.DeathRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeathRecordServiceImpl implements DeathRecordService {

    @Autowired
    private DeathRecordRepository deathRecordRepository;

    @Override
    public DeathRecord createDeathRecord(DeathRecord deathRecord) {
        return deathRecordRepository.save(deathRecord);
    }

    @Override
    public DeathRecord updateDeathRecord(Long id, DeathRecord deathRecord) {
        Optional<DeathRecord> optionalDeathRecord = deathRecordRepository.findById(id);
        if (optionalDeathRecord.isPresent()) {
            DeathRecord existingDeathRecord = optionalDeathRecord.get();
            existingDeathRecord.setPerson(deathRecord.getPerson());
            existingDeathRecord.setDeathPlace(deathRecord.getDeathPlace());
            existingDeathRecord.setDeathDate(deathRecord.getDeathDate());
            existingDeathRecord.setCauseOfDeath(deathRecord.getCauseOfDeath());
            existingDeathRecord.setDeceasedName(deathRecord.getDeceasedName());
            existingDeathRecord.setDeceasedAge(deathRecord.getDeceasedAge());
            existingDeathRecord.setDeceasedGender(deathRecord.getDeceasedGender());
            existingDeathRecord.setDeclarationDate(deathRecord.getDeclarationDate());
            existingDeathRecord.setDeclarationPlace(deathRecord.getDeclarationPlace());
            existingDeathRecord.setInformantName(deathRecord.getInformantName());
            existingDeathRecord.setInformantRelationship(deathRecord.getInformantRelationship());
            existingDeathRecord.setFuneralPlace(deathRecord.getFuneralPlace());
            existingDeathRecord.setCemeteryName(deathRecord.getCemeteryName());
            return deathRecordRepository.save(existingDeathRecord);
        } else {
            throw new RuntimeException("DeathRecord not found with id " + id);
        }
    }

    @Override
    public void deleteDeathRecord(Long id) {
        deathRecordRepository.deleteById(id);
    }

    @Override
    public DeathRecord getDeathRecordById(Long id) {
        return deathRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("DeathRecord not found with id " + id));
    }

    @Override
    public List<DeathRecord> getAllDeathRecords() {
        return deathRecordRepository.findAll();
    }
}