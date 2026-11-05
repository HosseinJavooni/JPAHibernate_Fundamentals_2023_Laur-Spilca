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

            // Using parameters
            TypedQuery<Product> typedQuery = entityManager.createQuery(jpql, Product.class);
            typedQuery.setParameter("name", "%ul%");
            typedQuery.setParameter("price", 200);
            List<Product> products = typedQuery.getResultList();
            System.out.println(products);

            // Using functions
            var jpql2 = "select AVG(p.price) from Product p";
            TypedQuery<Product> typedQuery2 = entityManager.createQuery(jpql2, Product.class);
            System.out.println("Function result: " + typedQuery2.getSingleResult());

            // Get selected results
            var jpql3 = "select p.price, p.name from Product p";
            TypedQuery<Object[]> typedQuery3 = entityManager.createQuery(jpql3, Object[].class);
            typedQuery3.getResultList().forEach(r -> {
                System.out.println(r[0] + " " + r[1]);
            });

            // Using group by
            var jpql4 = "select p.price, AVG(p.price) from Product p GROUP BY p.price";
            TypedQuery<Object[]> typedQuery4 = entityManager.createQuery(jpql4, Object[].class);
            typedQuery4.getResultList().forEach(r -> {
                System.out.println(r[0] + " " + r[1]);});


            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}