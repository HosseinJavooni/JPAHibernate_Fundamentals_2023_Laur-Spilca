package example.org;

import example.org.entity.Comment;
import example.org.entity.Post;
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
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");

        // With Class config file in the project:
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), properties);
        // Represent the context of Hibernate (Like the context of Spring Framework)
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Post post = new Post();
            post.setTitle("post 1");
            post.setContent("Hello post 1");

            Comment comment = new Comment();
            comment.setContent("Post 1 comment");
//            comment.setPost(post);


            post.setComments(List.of(comment));

            entityManager.persist(post);
            entityManager.persist(comment);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}