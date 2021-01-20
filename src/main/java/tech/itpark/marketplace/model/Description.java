package tech.itpark.marketplace.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "operation_system")
    private String operationSystem;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "color")
    private String color;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "model")
    private String model;

    @NotNull(message = "Данное поле не должно быть пустым")
    @Min(value = 1,  message = "Минимальный размер экрана 1\"")
    @Column(name = "screen_size")
    private double screenSize;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "resolution")
    private String resolution;
}
