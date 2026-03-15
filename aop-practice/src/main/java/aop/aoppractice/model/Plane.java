package aop.aoppractice.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "plane")
@Getter
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String model;
    @Column(name = "weight")
    private int weight;
    @Column(name = "totalSpeed")
    private int totalSpeed;
    @Column(name = "numberOfSeats")
    private int numberOfSeats;
}