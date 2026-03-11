package org.example.springbootpractice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.dto.ElementDto;
import org.example.springbootpractice.mapper.ElementMapper;
import org.example.springbootpractice.model.Element;
import org.example.springbootpractice.service.ElementCRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements")
@RequiredArgsConstructor
public class ElementController {
    private final ElementCRUDService elementCRUDService;
    private final ElementMapper elementMapper;

    @PostMapping("/save")
    public ResponseEntity<ElementDto> saveElement(@Valid @RequestBody ElementDto elementDto) {
        return ResponseEntity.ok(elementCRUDService.saveElement(elementDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementDto> getElementById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(elementMapper.toDto(elementCRUDService.getElementById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ElementDto>> getAllElements() {
        return ResponseEntity.ok(elementCRUDService.findAllElementDto());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ElementDto> upgradeElement(@PathVariable("id") Long id, @Valid @RequestBody ElementDto elementDto) {
        Element element = elementCRUDService.upgradeElement(id, elementDto);
        return ResponseEntity.ok(elementMapper.toDto(element));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementDto> updateElement(@PathVariable("id") Long id, @Valid @RequestBody ElementDto elementDto) {
        Element updated = elementCRUDService.updateElement(id, elementDto);
        return ResponseEntity.ok(elementMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable("id") Long id) {
        elementCRUDService.deleteElement(id);
        return ResponseEntity.noContent().build();
    }
}