package org.example;

import org.example.entity.Enrollment;
import org.example.entity.Student;


public record EnrolledStudent(Student student, Enrollment enrollment) {

}
