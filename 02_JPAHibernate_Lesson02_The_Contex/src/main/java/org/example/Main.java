package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Employee;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

        EntityManager entityManager = entityManagerFactory.createEntityManager(); // represent the context of Hibernate (Like the context of Spring Framework)
        try {
            entityManager.getTransaction().begin();

//            Employee employee1 = new Employee();
//            employee1.setName("John");
//            employee1.setAddress("Tehran");
//            Employee employee2 = new Employee();
//            employee2.setName("Hossein");
//            employee2.setAddress("Tehran");
//            entityManager.persist(employee1);
//            entityManager.persist(employee2);
            Employee employee = entityManager.find(Employee.class, 1L);
            System.out.println("Employee: " + employee.toString());


            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}