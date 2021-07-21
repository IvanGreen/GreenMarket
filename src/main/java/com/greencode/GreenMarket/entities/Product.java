package com.greencode.GreenMarket.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<ProductImage> images;

    public void addImage(ProductImage productImage) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(productImage);
    }

    public Product( String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Product(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(String title) {
        this.title = title;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
