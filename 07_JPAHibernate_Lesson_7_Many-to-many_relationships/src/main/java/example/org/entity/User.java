package example.org.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Group> groups;
}
