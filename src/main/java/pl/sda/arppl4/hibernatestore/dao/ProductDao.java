package pl.sda.arppl4.hibernatestore.dao;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hibernatestore.model.Product;
import pl.sda.arppl4.hibernatestore.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements IProductDao {
    @Override
    public void dodajProduct(Product product) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(product);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void usunProduct(Product product) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()){
            Transaction transaction = session.beginTransaction();

            session.remove(product);

            transaction.commit();
        }
    }

    @Override
    public Optional<Product> zwrocProduct(Long id) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            Product obiektProduct = session.get(Product.class, id);

            return Optional.ofNullable(obiektProduct);
        }
    }

    @Override
    public List<Product> zwrocListeProducts() {
        List<Product> productList = new ArrayList<>();

        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            TypedQuery<Product> zapytanie = session.createQuery("from Product", Product.class);
            List<Product> wynikZapytania = zapytanie.getResultList();

            productList.addAll(wynikZapytania);
        } catch (SessionException sessionException) {
            System.err.println("Błąd wczytywania danych.");
        }

        return productList;
    }

    @Override
    public void updateProduct(Product product) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(product);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null){
                transaction.rollback();
            }
        }
    }
}
