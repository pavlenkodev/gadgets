package tech.itpark.marketplace.model;

import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "image")
    private String image;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "vendor")
    private String vendor;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "category")
    private String category;

    @Min(value = 1, message = "Цена продукта должна быть не меньше 1")
    @Column(name = "price")
    private int price;

    @Min(value = 1, message = "Минимальное количество оперативной памяти 1 ГБ")
    @Column(name = "ram")
    private int ram;

    @NotEmpty(message = "Данное поле не должно быть пустым")
    @Column(name = "cpu")
    private String cpu;

    @Min(value = 1, message = "Минимальное количество ядер 1")
    @Column(name = "cores")
    private int cores;

    @NotNull(message = "Данное поле не должно быть пустым")
    @Min(value = 1, message = "Объем памяти должен быть не менее 1 ГБ")
    @Column(name = "storage")
    private int storage;

    @NotNull(message = "Данное поле не должно быть пустым. Создайте описание этого продукта и введите его номер")
    @ManyToOne
    @JoinColumn(name = "description_id")
    private Description descriptionId;

    public Product() {
    }

    public Product(String vendor, String category, int price) {
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }
}
