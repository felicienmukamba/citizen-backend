package com.soside.backend.controllers;
import com.soside.backend.dto.BirthRecordDTO;
import com.soside.backend.mappers.BirthRecordMapper;
import com.soside.backend.models.BirthRecord;
import com.soside.backend.services.birthrecord.BirthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/birth-records")
public class BirthRecordController {

    @Autowired
    private BirthRecordService birthRecordService;

    @Autowired
    private BirthRecordMapper birthRecordMapper;

    @PostMapping
    public ResponseEntity<BirthRecordDTO> createBirthRecord(@RequestBody BirthRecordDTO birthRecordDTO) {
        BirthRecord birthRecord = birthRecordMapper.toEntity(birthRecordDTO);
        BirthRecord createdBirthRecord = birthRecordService.createBirthRecord(birthRecord);
        BirthRecordDTO createdBirthRecordDTO = birthRecordMapper.toDTO(createdBirthRecord);
        return ResponseEntity.ok(createdBirthRecordDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthRecordDTO> updateBirthRecord(@PathVariable Long id, @RequestBody BirthRecordDTO birthRecordDTO) {
        BirthRecord birthRecord = birthRecordMapper.toEntity(birthRecordDTO);
        BirthRecord updatedBirthRecord = birthRecordService.updateBirthRecord(id, birthRecord);
        BirthRecordDTO updatedBirthRecordDTO = birthRecordMapper.toDTO(updatedBirthRecord);
        return ResponseEntity.ok(updatedBirthRecordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthRecord(@PathVariable Long id) {
        birthRecordService.deleteBirthRecord(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BirthRecordDTO> getBirthRecordById(@PathVariable Long id) {
        BirthRecord birthRecord = birthRecordService.getBirthRecordById(id);
        BirthRecordDTO birthRecordDTO = birthRecordMapper.toDTO(birthRecord);
        return ResponseEntity.ok(birthRecordDTO);
    }

    @GetMapping
    public ResponseEntity<List<BirthRecordDTO>> getAllBirthRecords() {
        List<BirthRecord> birthRecords = birthRecordService.getAllBirthRecords();
        List<BirthRecordDTO> birthRecordDTOs = birthRecords.stream()
                .map(birthRecordMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(birthRecordDTOs);
    }
}