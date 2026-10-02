package org.example.keys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class CustomerKey implements Serializable {
    Long number;
    String firstName;
    String lastName;
}
