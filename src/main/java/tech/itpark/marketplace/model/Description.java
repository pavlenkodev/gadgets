package tech.itpark.marketplace.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operationSystem;
    private String color;
    private String model;
    private double screenSize;
    private String resolution;
}
