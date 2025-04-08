package com.soside.backend.controllers;
import com.soside.backend.dto.CriminalRecordDTO;
import com.soside.backend.mappers.CriminalRecordMapper;
import com.soside.backend.models.CriminalRecord;
import com.soside.backend.services.criminalrecord.ICriminalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/criminal-records")
public class CriminalRecordController {

    @Autowired
    private ICriminalRecordService criminalRecordService;

    @GetMapping
    public List<CriminalRecordDTO> getAllCriminalRecords() {
        return criminalRecordService.getAllCriminalRecords().stream()
                .map(CriminalRecordMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriminalRecordDTO> getCriminalRecordById(@PathVariable Long id) {
        CriminalRecord criminalRecord = criminalRecordService.getCriminalRecordById(id);
        CriminalRecordDTO criminalRecordDTO = CriminalRecordMapper.INSTANCE.toDTO(criminalRecord);
        return ResponseEntity.ok(criminalRecordDTO);
    }

    @PostMapping
    public ResponseEntity<CriminalRecordDTO> createCriminalRecord(@RequestBody CriminalRecordDTO criminalRecordDTO) {
        CriminalRecord criminalRecord = CriminalRecordMapper.INSTANCE.toEntity(criminalRecordDTO);
        CriminalRecord createdCriminalRecord = criminalRecordService.createCriminalRecord(criminalRecord);
        CriminalRecordDTO createdCriminalRecordDTO = CriminalRecordMapper.INSTANCE.toDTO(createdCriminalRecord);
        return ResponseEntity.ok(createdCriminalRecordDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriminalRecordDTO> updateCriminalRecord(@PathVariable Long id, @RequestBody CriminalRecordDTO criminalRecordDTO) {
        CriminalRecord criminalRecord = CriminalRecordMapper.INSTANCE.toEntity(criminalRecordDTO);
        CriminalRecord updatedCriminalRecord = criminalRecordService.updateCriminalRecord(id, criminalRecord);
        CriminalRecordDTO updatedCriminalRecordDTO = CriminalRecordMapper.INSTANCE.toDTO(updatedCriminalRecord);
        return ResponseEntity.ok(updatedCriminalRecordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCriminalRecord(@PathVariable Long id) {
        criminalRecordService.deleteCriminalRecord(id);
        return ResponseEntity.noContent().build();
    }
}