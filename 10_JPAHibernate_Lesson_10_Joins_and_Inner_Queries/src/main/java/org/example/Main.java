package org.example;

import jakarta.persistence.TypedQuery;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String,Object> properties = new HashMap<>();

        /**
         * These properties can be introduced in getProperties() method in PersistenceUnitInfo interface
         * implementation (here in org.org.example.org.persistence.CustomPersistenceUnitInfo class)
         */
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            /*Course course = new Course();
            course.setName("Course 3");
            entityManager.persist(course);
            Student student = new Student();
            student.setName("Hossein 3");
            student.setAge(42);
            Enrollment enrollment = new Enrollment();
            enrollment.setCourse(course);
            enrollment.setStudent(student);
            enrollment.setDateOfEnrollment(new Date());
            entityManager.persist(enrollment);*/

            String jpql2 = "select c, e from Course c LEFT JOIN Enrollment e on e.course = c";
//            String jpql2 = "select c from Course c LEFT JOIN Enrollment e on e.course = c WHERE c.id = :courseId";
            TypedQuery<Object[]> query1 = entityManager.createQuery(jpql2, Object[].class);
            List<Object[]> courses = query1.getResultList();
            courses.forEach(o -> System.out.println(o[0] + " " + o[1]));

            String jpql = "select new org.example.EnrolledStudent(s, e) from Student s LEFT JOIN Enrollment e on e.student = s";
            TypedQuery<EnrolledStudent> query2 = entityManager.createQuery(jpql, EnrolledStudent.class);
            List<EnrolledStudent> enrolledStudents = query2.getResultList();
            for (EnrolledStudent e : enrolledStudents) {
                System.out.println(e);
            }

            System.out.println("===============(3)=================");
            String jpql3 = """ 
            SELECT new org.example.EnrolledStudent(s, e) FROM
            Student s LEFT JOIN Enrollment e ON e.student = s
            WHERE s IN (SELECT s FROM Student WHERE s.age > :studentAge)
            """;
            TypedQuery<EnrolledStudent> query3 = entityManager.createQuery(jpql3, EnrolledStudent.class);
            query3.setParameter("studentAge", 40);
            List<EnrolledStudent> enrolledStudents_3 = query3.getResultList();
            for (EnrolledStudent e : enrolledStudents_3) {
                System.out.println(e);
            }

            System.out.println("===============(4)=================");
            String jpql4 = """ 
            SELECT s, (SELECT COUNT(e) FROM Enrollment e WHERE e.student = s) FROM
            Student s
            """;
            TypedQuery<Object[]> query4 = entityManager.createQuery(jpql4, Object[].class);
            List<Object[]> enrolledStudents_4 = query4.getResultList();
            for (Object[] e : enrolledStudents_4) {
                System.out.println(e[0] + " ==> " + e[1]);
            }


//

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}