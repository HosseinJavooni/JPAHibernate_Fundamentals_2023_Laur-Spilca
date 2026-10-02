package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Customer;
import org.example.entity.Employee;
import org.example.entity.Product;
import org.example.keys.CustomerKey;
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
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Employee employee = new Employee();
            employee.setName("Hossein");
            employee.setAddress("Tehran");

            Product product = new Product();
            product.setName("Harry Potter");
            product.setId("n1");
            product.setPrice(7800L);

            Product product1 = new Product();
            product1.setName("Harry Potter");
            product1.setId("n2");
            product1.setPrice(9800L);


            CustomerKey customerKey = new CustomerKey();
            customerKey.setFirstName("Mahan");
            customerKey.setLastName("Jabani");
            customerKey.setNumber(123L);
            Customer customer = new Customer();
            customer.setCustomerKey(customerKey);
            customer.setTotalPurchaseAmount(340000L);

            entityManager.persist(employee);
            entityManager.persist(product);
            entityManager.persist(product1);
            entityManager.persist(customer);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}