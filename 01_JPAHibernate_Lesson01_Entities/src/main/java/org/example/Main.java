package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // With persistence.xml persistence config file at "/src/resourdes/META-INF/persistence.xml" file
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit"); // Get from /src/resources/META-INF/persistence.xml file --> <persistence-unit name="myPersistenceUnit" transaction-type="RESOURCE_LOCAL">
        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

        EntityManager entityManager = entityManagerFactory.createEntityManager(); // represent the context of Hibernate (Like the context of Spring Framework)
        try {
            entityManager.getTransaction().begin();

            Product product = new Product();
            product.setId(3L);
            product.setProductName("pan");

            entityManager.persist(product); // add to the Hibernate context (IT IS NOT AN INSERT QUERY!)

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}