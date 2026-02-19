package org.example.controller;

import org.example.dto.ItemDto;
import org.example.mapper.ItemMapper;
import org.example.model.Item;
import org.example.service.ItemCRUDServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemCRUDServices itemCRUDServices;
    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/getall")
    public List<ItemDto> getAllItems() {
        return itemCRUDServices.findAll().stream().map(itemMapper::toDto).toList();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemMapper.toDto(itemCRUDServices.findById(id).orElseThrow()));
    }

    @PostMapping("/save")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemCRUDServices.save(item));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        Item updated = itemCRUDServices.update(id, itemDto);
        return ResponseEntity.ok(itemMapper.toDto(updated));
    }

    @PatchMapping("/upgrade/{id}")
    public ResponseEntity<ItemDto> partialUpdateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        Item updated = itemCRUDServices.partialUpdate(id, itemDto);
        return ResponseEntity.ok(itemMapper.toDto(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        itemCRUDServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}