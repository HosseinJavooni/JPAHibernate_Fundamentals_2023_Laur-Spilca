package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PASSPORT")
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String address;

    @OneToOne(mappedBy = "passport")
    @JoinColumn(name = "PERSON")
    private Person person;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", name='" + number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
