package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.keys.ProductKey;

@Entity
@Table(name = "PRODUCT")
@IdClass(ProductKey.class)
@Getter
@Setter
public class Product {
    @Id @Column(name = "ID")
    private String id;
    @Id @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Long price;
}
