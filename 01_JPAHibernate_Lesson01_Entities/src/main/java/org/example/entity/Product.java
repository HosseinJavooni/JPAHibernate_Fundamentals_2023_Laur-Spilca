package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "PRODUCT_NAME")
    private String productName;
}
