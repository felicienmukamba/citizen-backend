package com.soside.backend.services.birthrecord;
import com.soside.backend.models.BirthRecord;
import com.soside.backend.repositories.BirthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirthRecordServiceImpl implements BirthRecordService {

    @Autowired
    private BirthRecordRepository birthRecordRepository;

    @Override
    public BirthRecord createBirthRecord(BirthRecord birthRecord) {
        return birthRecordRepository.save(birthRecord);
    }

    @Override
    public BirthRecord updateBirthRecord(Long id, BirthRecord birthRecord) {
        Optional<BirthRecord> optionalBirthRecord = birthRecordRepository.findById(id);
        if (optionalBirthRecord.isPresent()) {
            BirthRecord existingBirthRecord = optionalBirthRecord.get();
            existingBirthRecord.setPerson(birthRecord.getPerson());
            existingBirthRecord.setBirthPlace(birthRecord.getBirthPlace());
            existingBirthRecord.setDeclarationDate(birthRecord.getDeclarationDate());
            existingBirthRecord.setDeclarationPlace(birthRecord.getDeclarationPlace());
            existingBirthRecord.setFatherName(birthRecord.getFatherName());
            existingBirthRecord.setMotherName(birthRecord.getMotherName());
            existingBirthRecord.setInformantName(birthRecord.getInformantName());
            existingBirthRecord.setInformantRelationship(birthRecord.getInformantRelationship());
            existingBirthRecord.setRegistrationNumber(birthRecord.getRegistrationNumber());
            existingBirthRecord.setDeliveryMethod(birthRecord.getDeliveryMethod());
            existingBirthRecord.setBirthOrder(birthRecord.getBirthOrder());
            existingBirthRecord.setHospitalName(birthRecord.getHospitalName());
            return birthRecordRepository.save(existingBirthRecord);
        } else {
            throw new RuntimeException("BirthRecord not found with id " + id);
        }
    }

    @Override
    public void deleteBirthRecord(Long id) {
        birthRecordRepository.deleteById(id);
    }

    @Override
    public BirthRecord getBirthRecordById(Long id) {
        return birthRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("BirthRecord not found with id " + id));
    }

    @Override
    public List<BirthRecord> getAllBirthRecords() {
        return birthRecordRepository.findAll();
    }
}