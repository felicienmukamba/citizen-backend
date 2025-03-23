package com.soside.backend.mappers;

import com.soside.backend.dto.CriminalRecordDTO;
import com.soside.backend.models.CriminalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CriminalRecordMapper {
    CriminalRecordMapper INSTANCE = Mappers.getMapper(CriminalRecordMapper.class);

    CriminalRecordDTO toDTO(CriminalRecord criminalRecord);
    CriminalRecord toEntity(CriminalRecordDTO criminalRecordDTO);
}