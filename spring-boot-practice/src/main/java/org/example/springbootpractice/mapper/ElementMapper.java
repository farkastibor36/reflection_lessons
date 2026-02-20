package org.example.springbootpractice.mapper;

import org.example.springbootpractice.dto.ElementDto;
import org.example.springbootpractice.model.Element;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ElementMapper {
    ElementDto toDto(Element element);

    Element toEntity(ElementDto elementDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchElement(ElementDto elementDto, @MappingTarget Element element);
}