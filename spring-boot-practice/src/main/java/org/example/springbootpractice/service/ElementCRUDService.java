package org.example.springbootpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.dto.ElementDto;
import org.example.springbootpractice.mapper.ElementMapper;
import org.example.springbootpractice.model.Element;
import org.example.springbootpractice.repository.ElementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementCRUDService {
    private final ElementRepository elementRepository;
    private final ElementMapper elementMapper;

    public Element saveElement(Element element) {
        return elementRepository.save(element);
    }

    public List<ElementDto> findAllElementDto() {
        return elementRepository.findAll().stream().map(elementMapper::toDto).toList();
    }

    public Element getElementById(Long id) {
        return elementRepository.findById(id).orElseThrow();
    }

    public void deleteElement(Long id) {
        elementRepository.deleteById(id);
    }

    @Transactional
    public Element upgradeElement(Long id, Element element) {
        Element elementToUpdate = getElementById(id);
        return elementRepository.save(elementToUpdate);
    }

    @Transactional
    public Element updateElement(Long id, ElementDto elementDto) {
        Element element = elementRepository.findById(id).orElseThrow();
        elementMapper.patchElement(elementDto, element);
        return elementRepository.save(element);
    }
}