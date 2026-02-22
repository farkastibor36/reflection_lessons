package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ItemDto;
import org.example.mapper.ItemMapper;
import org.example.model.Item;
import org.example.service.ItemCRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemCRUDService itemCRUDService;
    private final ItemMapper itemMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> items = itemCRUDService.findAllDto();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemCRUDService.findById(id).orElseThrow());
    }

    @PostMapping("/save")
    public ResponseEntity<ItemDto> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemCRUDService.save(item).orElseThrow(() -> new IllegalStateException("Item doesn't save")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        Item updated = itemCRUDService.update(id, itemDto);
        return ResponseEntity.ok(itemMapper.toDto(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> partialUpdateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        Item updated = itemCRUDService.partialUpdate(id, itemDto);
        return ResponseEntity.ok(itemMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        itemCRUDService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}