package com.soside.backend.mappers;
import com.soside.backend.dto.ComplaintDTO;
import com.soside.backend.models.Complaint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComplaintMapper {
    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    ComplaintDTO toDTO(Complaint complaint);
    Complaint toEntity(ComplaintDTO complaintDTO);
}