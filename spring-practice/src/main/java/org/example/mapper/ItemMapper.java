package org.example.mapper;

import org.example.dto.ItemDto;
import org.example.model.Item;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(Item item);

    void updateItem(ItemDto itemDto, @MappingTarget Item item);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchItem(ItemDto itemDto, @MappingTarget Item item);
}