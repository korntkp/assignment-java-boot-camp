package io.github.korntkp.ecommercesystem.product;

import io.github.korntkp.ecommercesystem.product.Product;
import io.github.korntkp.ecommercesystem.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("findAllByName: ส่ง name=Testing แล้วจะต้องได้รับ 2 Product ที่มีคำว่า Testing")
    void case01() {
        // Arrange
        Product product1 = new Product(1, "Testing");
        Product product2 = new Product(2, "Test22");
        Product product3 = new Product(3, "Testing3");
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // Act
        List<Product> result = productRepository.findAllByName("Testing");

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("findAllByName: ส่ง name=mock แล้วจะต้องไม่พบ Product")
    void case02() {
        // Arrange
        Product product1 = new Product(1, "Testing");
        Product product2 = new Product(2, "Test22");
        Product product3 = new Product(3, "Testing3");
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // Act
        List<Product> result = productRepository.findAllByName("mock");

        // Assert
        assertEquals(0, result.size());
    }
}