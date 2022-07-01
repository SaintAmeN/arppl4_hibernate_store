package pl.sda.arppl4.hibernatestore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    private String name;
    private Double price;
    private String producent;
    private LocalDate expiryDate;
    private Double quantity;
    private ProductUnit unit; // FIXME: nie działam dobrze...
}
