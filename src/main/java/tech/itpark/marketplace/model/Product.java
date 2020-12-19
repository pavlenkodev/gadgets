package tech.itpark.marketplace.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private int price;
    @Column(name="ram")
    private int ram;
    @Column(name = "cpu")
    private String cpu;
    @Column(name = "cores")
    private int cores;
    @Column(name="storage")
    private  int storage;
    @ManyToOne
    @JoinColumn(name= "description_id")
    private Description descriptionId;

    public Product() {
    }

    public Product(String vendor, String category, int price) {
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }
}
