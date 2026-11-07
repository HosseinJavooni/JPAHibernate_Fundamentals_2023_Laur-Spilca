package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "ENROLLMENT")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATE_OF_ENROLLMENT")
    private Date dateOfEnrollment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Student student;

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", dateOfEnrollment=" + dateOfEnrollment +
                ", courseId=" + course.getId() +
                ", studentId=" + student.getId() +
                '}';
    }
}
