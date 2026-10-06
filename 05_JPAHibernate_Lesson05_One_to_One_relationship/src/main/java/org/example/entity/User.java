package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SecondaryTable(
        name = "USER_DESCRIPTION",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(table = "USER_DESCRIPTION")
    private String description;
    @Column(table = "USER_DESCRIPTION")
    private String extraDescription;
}
