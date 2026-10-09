package example.org;

import example.org.entity.Group;
import example.org.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import example.org.persistence.CustomPersistenceUnitInfo;
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
//        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            User user1 = new User();
            user1.setName("user1");

            User user2 = new User();
            user2.setName("user2");

            Group group1 = new Group();
            group1.setName("group1");

            Group group2 = new Group();
            group2.setName("group2");

            group1.setUsers(List.of(user1, user2));
            group2.setUsers(List.of(user2));

            entityManager.persist(user1);
            entityManager.persist(user2);
            entityManager.persist(group1);
            entityManager.persist(group2);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}