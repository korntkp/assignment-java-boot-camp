package io.github.korntkp.ecommercesystem;

import io.github.korntkp.ecommercesystem.product.Product;
import io.github.korntkp.ecommercesystem.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ECommerceSystemApplication {
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void xyz() {
        Product product1 = new Product(1, "Samsung S9", "Samsung Galaxy S9", "This is Android mobile phone.");
        Product product2 = new Product(2, "Apple Pencil", "Apple Pencil", "This is Apple Pencil.");
        Product product3 = new Product(3, "Samsung Note 10", "Samsung Galaxy Note10", "This is Android mobile phone with pencil.");
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    public static void main(String[] args) {
        SpringApplication.run(ECommerceSystemApplication.class, args);
    }

}
