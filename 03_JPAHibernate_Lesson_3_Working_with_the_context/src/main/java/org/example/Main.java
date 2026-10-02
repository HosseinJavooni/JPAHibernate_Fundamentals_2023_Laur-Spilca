package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Employee;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String,Object> properties = new HashMap<>();

        /**
         * These properties can be introduced in getProperties() method in PersistenceUnitInfo interface
         * implementation (here in org.example.persistence.CustomPersistenceUnitInfo class)
         */
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

//            Employee employee = entityManager.find(Employee.class, 3L);
//            System.out.println("Employee: " + employee.toString());
//            employee.setName("Saeed");
//            employee.setName("Jack");

            Employee employee = new Employee();
            employee.setId(6L);
            employee.setAddress("Tehran-Ray");
            employee.setName("Hossein Jabani");
            entityManager.merge(employee);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}