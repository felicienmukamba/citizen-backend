package com.soside.backend.mappers;
import com.soside.backend.dto.DeathRecordDTO;
import com.soside.backend.models.DeathRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeathRecordMapper {
    DeathRecordMapper INSTANCE = Mappers.getMapper(DeathRecordMapper.class);

    DeathRecordDTO toDTO(DeathRecord deathRecord);
    DeathRecord toEntity(DeathRecordDTO deathRecordDTO);
}