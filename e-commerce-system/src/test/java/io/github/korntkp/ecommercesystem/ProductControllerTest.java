package io.github.korntkp.ecommercesystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("Get Product List: ไม่ส่ง name แล้วจะต้องได้รับ List ที่มีทุก product")
    void case01() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        product1.setName("Test10");
        product2.setName("Test20");
        product3.setName("Test30");
        product4.setName("Test40");
        when(productRepository.findAll()).thenReturn(List.of(product1, product2, product3, product4));

        // Act
        Product[] resultList = testRestTemplate.getForObject("/products", Product[].class);

        // Assert, verify
        assertEquals(4, resultList.length);
    }

    @Test
    @DisplayName("Get Product List: ส่ง name = Test แล้วจะต้องได้รับ List ที่ product name มีคำว่า Test")
    void case02() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setName("Test10");
        product2.setName("Test20");
        when(productRepository.findAllByName("Test")).thenReturn(List.of(product1, product2));

        // Act
        Product[] resultList = testRestTemplate.getForObject("/products/Test", Product[].class);

        // Assert, verify
        assertEquals(2, resultList.length);
        assertEquals("Test10", resultList[0].getName());
        assertEquals("Test20", resultList[1].getName());
    }

    @Test
    @DisplayName("Get Product List: ส่ง name = Test แล้วจะต้องได้รับ array เปล่า")
    void case03() {
        // Arrange
        when(productRepository.findAllByName("Test")).thenReturn(List.of());

        // Act
        Product[] resultList = testRestTemplate.getForObject("/products/Test", Product[].class);

        // Assert, verify
        assertEquals(0, resultList.length);
    }

    @Test
    @DisplayName("Get Product Detail: ส่ง Id = 2 แล้วจะต้องได้รับ product ชื่อ Testing")
    void case04() {
        // Arrange
        Product product = new Product();
        product.setName("Testing");
        when(productRepository.findById(2)).thenReturn(Optional.of(product));

        // Act
        Product result = testRestTemplate.getForObject("/product/2", Product.class);

        // Assert, verify
        assertEquals("Testing", result.getName());
    }

    @Test
    @DisplayName("Get Product Detail: ส่ง Id = 99 แล้วจะต้องได้รับ Error not found")
    void case05() {
        // Arrange

        // Act
        ErrorResponse result = testRestTemplate.getForObject("/product/99", ErrorResponse.class);

        // Assert, verify
        assertEquals(404, result.getCode());
        assertEquals("Product=99 not found", result.getMessage());
    }
}
