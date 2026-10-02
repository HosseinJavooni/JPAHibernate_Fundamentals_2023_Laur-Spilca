package org.example.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.keys.CustomerKey;

@Entity
@Getter
@Setter
@Table(name = "CUSTOMER")
public class Customer {
    @EmbeddedId
    private CustomerKey customerKey;
    private Long totalPurchaseAmount;
}
