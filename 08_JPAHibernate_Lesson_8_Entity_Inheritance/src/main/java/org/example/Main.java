package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Book;
import org.example.entity.ElectronicDevices;
import org.example.entity.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

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
         * implementation (here in org.example.org.persistence.CustomPersistenceUnitInfo class)
         */
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Book book = new Book();
            book.setAuthor("Jack");
            book.setId(1L);
            book.setProductType("Printed");

            ElectronicDevices electronicDevices = new ElectronicDevices();
            electronicDevices.setId(2L);
            electronicDevices.setVoltage(220);
            electronicDevices.setProductType("Electronic");

            entityManager.persist(book);
            entityManager.persist(electronicDevices);
//            ElectronicDevices electronicDevices = entityManager.find(ElectronicDevices.class, 2);
//            System.out.println(electronicDevices);

//            var sql = "select p from Product p";
//            List<Product> products = entityManager.createQuery(sql, Product.class).getResultList();
//            System.out.println(products);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}