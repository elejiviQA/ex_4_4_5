package org.ot5usk.utils.db_utils;

import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@NoArgsConstructor
public class Util {

    private static SessionFactory sessionFactory;

    static {

        initSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static void initSessionFactory() {

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder
                .applySetting("hibernate-dialect", "org.hibernate.dialect.PostgresSQLDialect")
                .applySetting("connection.driver_class", "org.postgresql.Driver")
//                .applySetting("hibernate.hbm2ddl.auto", "validate")
                .applySetting("hibernate.connection.url", "jdbc:postgresql://localhost:5432/example_db")
                .applySetting("hibernate.connection.username","example_user")
                .applySetting("hibernate.connection.password", "!QAZxsw2")
                .applySetting("show_sql", "false")
                .applySetting("hibernate.format_sql", "true");
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry).addAnnotatedClasses(org.ot5usk.entities.Author.class, org.ot5usk.entities.Book.class);
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();

        sessionFactory = metadata.buildSessionFactory();
    }
}
