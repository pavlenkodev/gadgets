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
    @Column(name = "operation_system")
    private String operationSystem;
    @Column(name = "color")
    private String color;
    @Column(name = "model")
    private String model;
    @Column(name = "screen_size")
    private double screenSize;
    @Column(name = "resolution")
    private String resolution;
}
