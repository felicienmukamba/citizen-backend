package com.soside.backend.mappers;
import com.soside.backend.dto.CareHistoryDTO;
import com.soside.backend.models.CareHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CareHistoryMapper {
    CareHistoryMapper INSTANCE = Mappers.getMapper(CareHistoryMapper.class);

    CareHistoryDTO toDTO(CareHistory careHistory);
    CareHistory toEntity(CareHistoryDTO careHistoryDTO);
}