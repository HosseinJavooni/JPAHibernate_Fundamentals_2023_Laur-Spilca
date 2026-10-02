package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.generator.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Employee {
    @Id
    @GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;

    private String name;

    private String address;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
