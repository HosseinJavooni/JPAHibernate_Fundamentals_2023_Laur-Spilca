package org.example.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ElectronicDevices extends Product {

    private int voltage;
}
