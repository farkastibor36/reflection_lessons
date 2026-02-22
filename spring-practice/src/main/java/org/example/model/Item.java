package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private String name;
    @Column(name = "quantity")
    @Min(1)
    private int quantity;
    @Column(name = "price")
    @Positive(message = "Price must be positive")
    private int price;
    @Column(name = "description")
    @Size(min = 1, max = 1000)
    private String description;
}