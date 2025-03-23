package com.soside.backend.controllers;
import com.soside.backend.dto.DeathRecordDTO;
import com.soside.backend.mappers.DeathRecordMapper;
import com.soside.backend.models.DeathRecord;
import com.soside.backend.services.deathrecord.DeathRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/death-records")
public class DeathRecordController {

    @Autowired
    private DeathRecordService deathRecordService;

    @Autowired
    private DeathRecordMapper deathRecordMapper;

    @PostMapping
    public ResponseEntity<DeathRecordDTO> createDeathRecord(@RequestBody DeathRecordDTO deathRecordDTO) {
        DeathRecord deathRecord = deathRecordMapper.toEntity(deathRecordDTO);
        DeathRecord createdDeathRecord = deathRecordService.createDeathRecord(deathRecord);
        DeathRecordDTO createdDeathRecordDTO = deathRecordMapper.toDTO(createdDeathRecord);
        return ResponseEntity.ok(createdDeathRecordDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeathRecordDTO> updateDeathRecord(@PathVariable Long id, @RequestBody DeathRecordDTO deathRecordDTO) {
        DeathRecord deathRecord = deathRecordMapper.toEntity(deathRecordDTO);
        DeathRecord updatedDeathRecord = deathRecordService.updateDeathRecord(id, deathRecord);
        DeathRecordDTO updatedDeathRecordDTO = deathRecordMapper.toDTO(updatedDeathRecord);
        return ResponseEntity.ok(updatedDeathRecordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeathRecord(@PathVariable Long id) {
        deathRecordService.deleteDeathRecord(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeathRecordDTO> getDeathRecordById(@PathVariable Long id) {
        DeathRecord deathRecord = deathRecordService.getDeathRecordById(id);
        DeathRecordDTO deathRecordDTO = deathRecordMapper.toDTO(deathRecord);
        return ResponseEntity.ok(deathRecordDTO);
    }

    @GetMapping
    public ResponseEntity<List<DeathRecordDTO>> getAllDeathRecords() {
        List<DeathRecord> deathRecords = deathRecordService.getAllDeathRecords();
        List<DeathRecordDTO> deathRecordDTOs = deathRecords.stream()
                .map(deathRecordMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deathRecordDTOs);
    }
}