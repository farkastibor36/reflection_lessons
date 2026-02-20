package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ItemDto;
import org.example.mapper.ItemMapper;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemCRUDService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<ItemDto> findAllDto() {
        return itemRepository.findAll().stream().map(itemMapper::toDto).toList();
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow();
        itemMapper.updateItem(itemDto, item);
        return itemRepository.save(item);
    }

    @Transactional
    public Item partialUpdate(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow();
        itemMapper.patchItem(itemDto, item);
        return itemRepository.save(item);
    }
}