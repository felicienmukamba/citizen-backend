package com.soside.backend.mappers;
import com.soside.backend.dto.HealthRecordDTO;
import com.soside.backend.models.HealthRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface HealthRecordMapper {
    HealthRecordMapper INSTANCE = Mappers.getMapper(HealthRecordMapper.class);

    HealthRecordDTO toDTO(HealthRecord healthRecord);
    HealthRecord toEntity(HealthRecordDTO healthRecordDTO);
}