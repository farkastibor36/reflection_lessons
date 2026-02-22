package org.example.springbootpractice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "elements")
@Getter
@Setter
public class Element {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$")
    @NotBlank
    @NotNull
    private String name;
    @Column(name = "quantity")
    @Positive
    private int quantity;
    @Column(name = "price")
    @Positive
    private int price;
    @Column(name = "description")
    @Size(min = 1, max = 1000)
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$")
    private String description;
}