package pl.sda.arppl4.hibernatestore;

import pl.sda.arppl4.hibernatestore.dao.GenericDao;
import pl.sda.arppl4.hibernatestore.model.Product;
import pl.sda.arppl4.hibernatestore.parser.ProductCommandLineParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GenericDao<Product> productDao = new GenericDao<>();
//        GenericDao<Student> studentDao = new GenericDao<>();
//        GenericDao<Car> carDao = new GenericDao<>();

        ProductCommandLineParser parser = new ProductCommandLineParser(scanner, productDao);
        parser.parse();
    }
}
