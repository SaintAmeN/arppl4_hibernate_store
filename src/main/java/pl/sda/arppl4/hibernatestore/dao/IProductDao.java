package pl.sda.arppl4.hibernatestore.dao;

import pl.sda.arppl4.hibernatestore.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDao {
    // Create
    public void dodajProduct(Product product);

    // Delete
    public void usunProduct(Product product);

    // Read
    public Optional<Product> zwrocProduct(Long id);

    // Read
    public List<Product> zwrocListeProducts();

    // Update
    public void updateProduct(Product product);
}
