package org.example.springbootpractice.repository;

import org.example.springbootpractice.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}