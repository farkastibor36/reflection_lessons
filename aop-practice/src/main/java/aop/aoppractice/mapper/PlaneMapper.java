package aop.aoppractice.mapper;

import aop.aoppractice.dto.PlaneDto;
import aop.aoppractice.model.Plane;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaneMapper {
    PlaneDto toDto(Plane plane);
    Plane toEntity(PlaneDto planeDto);
}