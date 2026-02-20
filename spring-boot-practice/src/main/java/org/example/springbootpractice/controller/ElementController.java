package org.example.springbootpractice.controller;

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
    public ResponseEntity<Element> saveElement(@RequestBody Element element) {
        return ResponseEntity.ok(elementCRUDService.saveElement(element));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ElementDto> getElementById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(elementMapper.toDto(elementCRUDService.getElementById(id)));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ElementDto>> getAllElements() {
        return ResponseEntity.ok(elementCRUDService.findAllElementDto());
    }

    @PatchMapping("/upgrade/{id}")
    public ResponseEntity<Element> upgradeElement(@PathVariable("id") Long id, @RequestBody Element element) {
        return ResponseEntity.ok(elementCRUDService.upgradeElement(id, element));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ElementDto> updateElement(@PathVariable("id") Long id, @RequestBody ElementDto elementDto) {
        Element updated = elementCRUDService.updateElement(id, elementDto);
        return ResponseEntity.ok(elementMapper.toDto(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable("id") Long id) {
        elementCRUDService.deleteElement(id);
        return ResponseEntity.noContent().build();
    }
}