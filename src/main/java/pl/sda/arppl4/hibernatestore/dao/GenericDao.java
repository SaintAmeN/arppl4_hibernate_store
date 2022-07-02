package pl.sda.arppl4.hibernatestore.dao;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hibernatestore.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Typy Generyczne - nazwy:
// T - type
// K - key
// V - value
// E - element
public class GenericDao<T> {
    public void dodaj(T dodawanyObiekt) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(dodawanyObiekt);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void usun(T usuwanyObiekt) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.remove(usuwanyObiekt);

            transaction.commit();
        }
    }

    public Optional<T> znajdzPoId(Long id, Class<T> classType) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            T foundObject = session.get(classType, id);

            return Optional.ofNullable(foundObject);
        }
    }

    public List<T> list(Class<T> classType) {
        List<T> list = new ArrayList<>();

        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            TypedQuery<T> zapytanie = session.createQuery("from " + classType.getName(), classType);
            List<T> wynikZapytania = zapytanie.getResultList();

            list.addAll(wynikZapytania);
        } catch (SessionException sessionException) {
            System.err.println("Błąd wczytywania danych.");
        }

        return list;
    }

    public void aktualizuj(T obiektAktualizowany) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(obiektAktualizowany);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null){
                transaction.rollback();
            }
        }
    }
}
