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

    // If I don't use the mappedBy attribute with this definition
    // Hibernate create a column with the name PERSON in the PASSPORT table!
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
