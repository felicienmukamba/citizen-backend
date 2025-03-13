package com.soside.backend.services.marriageRecord;
import com.soside.backend.models.MarriageRecord;
import java.util.List;

public interface IMarriageRecordService {
    MarriageRecord saveMarriageRecord(MarriageRecord record);
    List<MarriageRecord> getAllMarriageRecords();
    MarriageRecord getMarriageRecordById(Long id);
    void deleteMarriageRecord(Long id);
}
