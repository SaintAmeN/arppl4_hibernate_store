package pl.sda.arppl4.hibernatestore.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        // Załadowanie "Registry" jako kolekcji parametrów konfiguracyjnych do rejestru.
        // Stworzenie obiektu zawierającego zestaw ustawień.
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        // Stworzenie obiektu metadata - dane opisujące połączenie z bazą danych.
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();

        // Wykorzystujemy metadane do skonfigurowania/parametryzacji fabryki. Tworzymy fabrykę.
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
