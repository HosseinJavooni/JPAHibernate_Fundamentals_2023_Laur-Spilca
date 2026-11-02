package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
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
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // SELECT, UPDATE, DELETE
//            var jpql = "select p from Product p";
//            var jpql = "select p from Product p where price < 200";
            var jpql= "select p from Product p where p.name like :name AND p.price < :price";
//            jpql= jpql.replace(":name", "'%ul%'");
//            jpql= jpql.replace(":price", "200");

            // SELECT p FROM Product p ==> Fetch all the attributes of the Product entity from the current context
            // SELECT * FROM product ==> Fetch all the columns from the table pruduct

            TypedQuery typedQuery = entityManager.createQuery(jpql, Product.class);
            typedQuery.setParameter("name", "%ul%");
            typedQuery.setParameter("price", 200);
            List<Product> products = typedQuery.getResultList();


            System.out.println(products);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}