package example.org.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "GROU")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "USER_GROU"
            , joinColumns = @JoinColumn(name = "GROU_ID")
            , inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<User> users;
}
