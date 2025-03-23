package com.soside.backend.mappers;
import com.soside.backend.dto.BirthRecordDTO;
import com.soside.backend.models.BirthRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BirthRecordMapper {
    BirthRecordMapper INSTANCE = Mappers.getMapper(BirthRecordMapper.class);

    BirthRecordDTO toDTO(BirthRecord birthRecord);
    BirthRecord toEntity(BirthRecordDTO birthRecordDTO);
}