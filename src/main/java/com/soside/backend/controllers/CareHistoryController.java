package com.soside.backend.controllers;
import com.soside.backend.dto.CareHistoryDTO;
import com.soside.backend.mappers.CareHistoryMapper;
import com.soside.backend.models.CareHistory;
import com.soside.backend.services.carehistory.ICareHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/care-histories")
public class CareHistoryController {

    @Autowired
    private ICareHistoryService careHistoryService;

    @GetMapping
    public List<CareHistoryDTO> getAllCareHistories() {
        return careHistoryService.getAllCareHistories().stream()
                .map(CareHistoryMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareHistoryDTO> getCareHistoryById(@PathVariable Long id) {
        CareHistory careHistory = careHistoryService.getCareHistoryById(id);
        CareHistoryDTO careHistoryDTO = CareHistoryMapper.INSTANCE.toDTO(careHistory);
        return ResponseEntity.ok(careHistoryDTO);
    }

    @PostMapping
    public ResponseEntity<CareHistoryDTO> createCareHistory(@RequestBody CareHistoryDTO careHistoryDTO) {
        CareHistory careHistory = CareHistoryMapper.INSTANCE.toEntity(careHistoryDTO);
        CareHistory createdCareHistory = careHistoryService.createCareHistory(careHistory);
        CareHistoryDTO createdCareHistoryDTO = CareHistoryMapper.INSTANCE.toDTO(createdCareHistory);
        return ResponseEntity.ok(createdCareHistoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareHistoryDTO> updateCareHistory(@PathVariable Long id, @RequestBody CareHistoryDTO careHistoryDTO) {
        CareHistory careHistory = CareHistoryMapper.INSTANCE.toEntity(careHistoryDTO);
        CareHistory updatedCareHistory = careHistoryService.updateCareHistory(id, careHistory);
        CareHistoryDTO updatedCareHistoryDTO = CareHistoryMapper.INSTANCE.toDTO(updatedCareHistory);
        return ResponseEntity.ok(updatedCareHistoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareHistory(@PathVariable Long id) {
        careHistoryService.deleteCareHistory(id);
        return ResponseEntity.noContent().build();
    }
}