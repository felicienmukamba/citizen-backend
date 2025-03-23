package com.soside.backend.controllers;
import com.soside.backend.models.MarriageRecord;
import com.soside.backend.services.marriageRecord.MarriageRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("marriage-records")
public class MarriageRecordController {

    private final MarriageRecordService service;

    public MarriageRecordController(MarriageRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MarriageRecord> createMarriageRecord(@RequestBody MarriageRecord record) {
        return ResponseEntity.ok(service.saveMarriageRecord(record));
    }

    @GetMapping
    public ResponseEntity<List<MarriageRecord>> getAllMarriageRecords() {
        return ResponseEntity.ok(service.getAllMarriageRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarriageRecord> getMarriageRecordById(@PathVariable Long id) {
        MarriageRecord record = service.getMarriageRecordById(id);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarriageRecord(@PathVariable Long id) {
        service.deleteMarriageRecord(id);
        return ResponseEntity.noContent().build();
    }
}
