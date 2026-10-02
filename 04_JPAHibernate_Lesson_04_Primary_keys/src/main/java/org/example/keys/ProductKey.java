package org.example.keys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductKey implements Serializable {
    private String id;
    private String name;
}
