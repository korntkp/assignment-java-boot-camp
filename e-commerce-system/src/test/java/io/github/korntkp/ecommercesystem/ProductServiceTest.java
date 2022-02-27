package io.github.korntkp.ecommercesystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("findProductById: ส่ง id = 1 แล้วจะต้องได้รับ Product ที่ชื่อ Testing")
    void case01() {
        // Arrange
        Product product = new Product();
        product.setName("Testing");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Product result = productService.findProductById(1); // id=2 -> Strict stubbing argument mismatch.

        // Assert
        assertEquals("Testing", result.getName());
    }

    @Test
    @DisplayName("findProductById: ส่ง id = 99 แล้วจะต้องได้รับ ProductNotFoundException 99")
    void case02() {
        // Arrange
        when(productRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Exception exception = assertThrows(ProductNotFoundException.class, () -> productService.findProductById(99));

        // Assert, verify
        String expectedMessage = "99";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("findAll: ไม่ส่ง name แล้วจะต้องได้รับทุก Product 3 ชิ้น")
    void case03() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        product1.setName("Testing1");
        product2.setName("Testing2");
        product3.setName("Testing3");
        product4.setName("Testing4");
        when(productRepository.findAll()).thenReturn(List.of(product1, product2, product3, product4));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.findAll();

        // Assert
        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("findProductsByName: ส่ง name=Test แล้วจะต้องได้รับ Product ที่ชื่อมีคำว่า Test")
    void case04() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setName("Testing1");
        product2.setName("Testing2");
        when(productRepository.findAllByName("Test")).thenReturn(List.of(product1, product2));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.findProductsByName("Test");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Testing1", result.get(0).getName());
    }
}