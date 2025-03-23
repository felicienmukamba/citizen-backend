package com.soside.backend.controllers;
import com.soside.backend.dto.HealthRecordDTO;
import com.soside.backend.mappers.HealthRecordMapper;
import com.soside.backend.models.HealthRecord;
import com.soside.backend.services.healthrecord.IHealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/health-records")
public class HealthRecordController {

    @Autowired
    private IHealthRecordService healthRecordService;

    @GetMapping
    public List<HealthRecordDTO> getAllHealthRecords() {
        return healthRecordService.getAllHealthRecords().stream()
                .map(HealthRecordMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthRecordDTO> getHealthRecordById(@PathVariable Long id) {
        HealthRecord healthRecord = healthRecordService.getHealthRecordById(id);
        HealthRecordDTO healthRecordDTO = HealthRecordMapper.INSTANCE.toDTO(healthRecord);
        return ResponseEntity.ok(healthRecordDTO);
    }

    @PostMapping
    public ResponseEntity<HealthRecordDTO> createHealthRecord(@RequestBody HealthRecordDTO healthRecordDTO) {
        HealthRecord healthRecord = HealthRecordMapper.INSTANCE.toEntity(healthRecordDTO);
        HealthRecord createdHealthRecord = healthRecordService.createHealthRecord(healthRecord);
        HealthRecordDTO createdHealthRecordDTO = HealthRecordMapper.INSTANCE.toDTO(createdHealthRecord);
        return ResponseEntity.ok(createdHealthRecordDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthRecordDTO> updateHealthRecord(@PathVariable Long id, @RequestBody HealthRecordDTO healthRecordDTO) {
        HealthRecord healthRecord = HealthRecordMapper.INSTANCE.toEntity(healthRecordDTO);
        HealthRecord updatedHealthRecord = healthRecordService.updateHealthRecord(id, healthRecord);
        HealthRecordDTO updatedHealthRecordDTO = HealthRecordMapper.INSTANCE.toDTO(updatedHealthRecord);
        return ResponseEntity.ok(updatedHealthRecordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable Long id) {
        healthRecordService.deleteHealthRecord(id);
        return ResponseEntity.noContent().build();
    }
}